package com.heou.comicgenerator.controller;

import com.heou.comicgenerator.common.Result;
import com.heou.comicgenerator.entity.SysRole;
import com.heou.comicgenerator.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class SysRoleController {

    @Autowired
    private SysRoleService roleService;

    @GetMapping("/list")
    public Result<List<SysRole>> list(SysRole role) {
        List<SysRole> list = roleService.selectRoleList(role);
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result<SysRole> getInfo(@PathVariable Integer id) {
        return Result.success(roleService.selectRoleById(id));
    }

    @PostMapping
    public Result add(@RequestBody SysRole role) {
        if (!roleService.checkRoleNameUnique(role)) {
            return Result.error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }
        if (!roleService.checkRoleCodeUnique(role)) {
            return Result.error("新增角色'" + role.getRoleName() + "'失败，角色编码已存在");
        }
        return Result.success(roleService.insertRole(role));
    }

    @PutMapping
    public Result edit(@RequestBody SysRole role) {
        if (!roleService.checkRoleNameUnique(role)) {
            return Result.error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }
        if (!roleService.checkRoleCodeUnique(role)) {
            return Result.error("修改角色'" + role.getRoleName() + "'失败，角色编码已存在");
        }
        return Result.success(roleService.updateRole(role));
    }

    @DeleteMapping("/{id}")
    public Result remove(@PathVariable Integer id) {
        if (roleService.countUserByRoleId(id)) {
            return Result.error("角色已被用户引用，不能删除");
        }
        return Result.success(roleService.deleteRoleById(id));
    }
}