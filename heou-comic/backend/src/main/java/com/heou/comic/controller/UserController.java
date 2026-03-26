package com.heou.comic.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heou.comic.common.PageResult;
import com.heou.comic.common.Result;
import com.heou.comic.entity.Role;
import com.heou.comic.entity.User;
import com.heou.comic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Result<PageResult<User>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Integer status) {
        Page<User> userPage = userService.getUserPage(page, size, username, status);
        return Result.success(new PageResult<>(userPage.getTotal(), userPage.getRecords()));
    }

    @PostMapping
    public Result<Void> create(@RequestBody User user) {
        userService.createUser(user);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return Result.success();
    }

    @GetMapping("/{userId}/roles")
    public Result<List<Role>> getUserRoles(@PathVariable Long userId) {
        List<Role> roles = userService.getUserRoles(userId);
        return Result.success(roles);
    }

    @PostMapping("/{userId}/roles")
    public Result<Void> assignUserRoles(@PathVariable Long userId, @RequestBody Map<String, List<Long>> data) {
        List<Long> roleIds = data.get("roleIds");
        userService.assignUserRoles(userId, roleIds);
        return Result.success();
    }
}
