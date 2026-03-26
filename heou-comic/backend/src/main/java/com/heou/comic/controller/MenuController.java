package com.heou.comic.controller;

import com.heou.comic.common.Result;
import com.heou.comic.entity.Menu;
import com.heou.comic.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public Result<List<Menu>> list() {
        List<Menu> menus = menuService.list();
        return Result.success(menus);
    }

    @GetMapping("/tree")
    public Result<List<Menu>> tree() {
        List<Menu> menus = menuService.getMenuTree();
        return Result.success(menus);
    }

    @PostMapping
    public Result<Void> create(@RequestBody Menu menu) {
        menuService.createMenu(menu);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody Menu menu) {
        menuService.updateMenu(id, menu);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return Result.success();
    }
}
