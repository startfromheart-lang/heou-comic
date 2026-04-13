package com.heou.comic.controller;

import com.heou.comic.common.PageResult;
import com.heou.comic.common.Result;
import com.heou.comic.entity.SysRole;
import com.heou.comic.service.SysRoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class SysRoleController {

    private final SysRoleService sysRoleService;

    public SysRoleController(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    @GetMapping("/list")
    public Result<PageResult<SysRole>> list(
            @RequestParam(required = false) String roleName,
            @RequestParam(required = false) String roleCode,
            @RequestParam(required = false) Integer status,
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size) {
        List<SysRole> list = sysRoleService.list(roleName, roleCode, status);
        int start = (int) ((current - 1) * size);
        int end = Math.min(start + size.intValue(), list.size());
        List<SysRole> pageList = list.subList(start, end);
        PageResult<SysRole> pageResult = PageResult.of(pageList, (long) list.size(), size, current);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result<SysRole> getById(@PathVariable Long id) {
        return Result.success(sysRoleService.getById(id));
    }

    @PostMapping
    public Result<Void> save(@RequestBody SysRole role) {
        sysRoleService.save(role);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(@RequestBody SysRole role) {
        sysRoleService.update(role);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        sysRoleService.deleteById(id);
        return Result.success();
    }
}
