package com.heou.comic.controller;

import com.heou.comic.common.PageResult;
import com.heou.comic.common.Result;
import com.heou.comic.entity.SysMenu;
import com.heou.comic.service.SysMenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class SysMenuController {

    private final SysMenuService sysMenuService;

    public SysMenuController(SysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }

    @GetMapping("/list")
    public Result<List<SysMenu>> list(
            @RequestParam(required = false) String menuName,
            @RequestParam(required = false) String menuCode,
            @RequestParam(required = false) Integer status) {
        return Result.success(sysMenuService.list(menuName, menuCode, status));
    }

    @GetMapping("/tree")
    public Result<List<SysMenu>> tree() {
        return Result.success(sysMenuService.list(null, null, null));
    }

    @GetMapping("/{id}")
    public Result<SysMenu> getById(@PathVariable Long id) {
        return Result.success(sysMenuService.getById(id));
    }

    @PostMapping
    public Result<Void> save(@RequestBody SysMenu menu) {
        sysMenuService.save(menu);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(@RequestBody SysMenu menu) {
        sysMenuService.update(menu);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        sysMenuService.deleteById(id);
        return Result.success();
    }
}
