package com.heou.comicgenerator.controller;

import com.heou.comicgenerator.common.Result;
import com.heou.comicgenerator.entity.SysMenu;
import com.heou.comicgenerator.entity.SysUser;
import com.heou.comicgenerator.mapper.SysUserMapper;
import com.heou.comicgenerator.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService menuService;

    @Autowired
    private SysUserMapper userMapper;

    @GetMapping("/list")
    public Result<List<SysMenu>> list(SysMenu menu) {
        List<SysMenu> menus = menuService.selectMenuList(menu);
        return Result.success(menus);
    }

    @GetMapping("/tree")
    public Result<List<SysMenu>> tree() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        SysUser user = userMapper.selectUserByUsername(username);
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getId());
        return Result.success(menus);
    }

    @GetMapping("/{id}")
    public Result<SysMenu> getInfo(@PathVariable Integer id) {
        return Result.success(menuService.selectMenuById(id));
    }

    @PostMapping
    public Result add(@RequestBody SysMenu menu) {
        if (!menuService.checkMenuNameUnique(menu)) {
            return Result.error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        return Result.success(menuService.insertMenu(menu));
    }

    @PutMapping
    public Result edit(@RequestBody SysMenu menu) {
        if (!menuService.checkMenuNameUnique(menu)) {
            return Result.error("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        return Result.success(menuService.updateMenu(menu));
    }

    @DeleteMapping("/{id}")
    public Result remove(@PathVariable Integer id) {
        if (menuService.hasChildByMenuId(id)) {
            return Result.error("存在子菜单,不允许删除");
        }
        if (menuService.checkMenuExistRole(id)) {
            return Result.error("菜单已被角色引用,不允许删除");
        }
        return Result.success(menuService.deleteMenuById(id));
    }
}