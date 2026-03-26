package com.heou.comicgenerator.controller;

import com.heou.comicgenerator.common.Result;
import com.heou.comicgenerator.entity.SysUser;
import com.heou.comicgenerator.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    @GetMapping("/list")
    public Result<List<SysUser>> list(SysUser user) {
        List<SysUser> list = userService.selectUserList(user);
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result<SysUser> getInfo(@PathVariable Integer id) {
        SysUser user = userService.selectUserById(id);
        user.setPassword(null);
        return Result.success(user);
    }

    @PostMapping
    public Result add(@RequestBody SysUser user) {
        if (!userService.checkUsernameUnique(user)) {
            return Result.error("新增用户'" + user.getUsername() + "'失败，登录账号已存在");
        }
        return Result.success(userService.insertUser(user));
    }

    @PutMapping
    public Result edit(@RequestBody SysUser user) {
        if (!userService.checkUsernameUnique(user)) {
            return Result.error("修改用户'" + user.getUsername() + "'失败，登录账号已存在");
        }
        return Result.success(userService.updateUser(user));
    }

    @DeleteMapping("/{id}")
    public Result remove(@PathVariable Integer id) {
        if (userService.checkAdminUser(id)) {
            return Result.error("不能删除最后一个超级管理员用户");
        }
        return Result.success(userService.deleteUserById(id));
    }
}