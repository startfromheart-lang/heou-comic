package com.heou.comic.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heou.comic.common.PageResult;
import com.heou.comic.common.Result;
import com.heou.comic.entity.Menu;
import com.heou.comic.entity.Role;
import com.heou.comic.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public Result<PageResult<Role>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String name) {
        Page<Role> rolePage = roleService.getRolePage(page, size, name);
        return Result.success(new PageResult<>(rolePage.getTotal(), rolePage.getRecords()));
    }

    @PostMapping
    public Result<Void> create(@RequestBody Role role) {
        roleService.createRole(role);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody Role role) {
        roleService.updateRole(id, role);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        roleService.deleteRole(id);
        return Result.success();
    }

    @GetMapping("/{roleId}/menus")
    public Result<List<Menu>> getRoleMenus(@PathVariable Long roleId) {
        List<Menu> menus = roleService.getRoleMenus(roleId);
        return Result.success(menus);
    }

    @PostMapping("/{roleId}/menus")
    public Result<Void> assignRoleMenus(@PathVariable Long roleId, @RequestBody Map<String, List<Long>> data) {
        List<Long> menuIds = data.get("menuIds");
        roleService.assignRoleMenus(roleId, menuIds);
        return Result.success();
    }
}
