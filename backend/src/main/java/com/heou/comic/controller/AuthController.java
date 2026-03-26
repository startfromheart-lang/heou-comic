package com.heou.comic.controller;

import com.heou.comic.common.LoginUser;
import com.heou.comic.common.Result;
import com.heou.comic.entity.SysMenu;
import com.heou.comic.entity.SysUser;
import com.heou.comic.service.SysMenuService;
import com.heou.comic.service.SysUserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final SysUserService sysUserService;
    private final SysMenuService sysMenuService;

    public AuthController(SysUserService sysUserService, SysMenuService sysMenuService) {
        this.sysUserService = sysUserService;
        this.sysMenuService = sysMenuService;
    }

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");
        LoginUser loginUser = sysUserService.login(username, password);
        List<SysMenu> menus = sysMenuService.getByUserId(loginUser.getUserId());
        Map<String, Object> result = new HashMap<>();
        result.put("user", loginUser);
        result.put("menus", menus);
        return Result.success(result);
    }

    @GetMapping("/info")
    public Result<Map<String, Object>> getUserInfo(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        SysUser user = sysUserService.getById(userId);
        List<SysMenu> menus = sysMenuService.getByUserId(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        result.put("menus", menus);
        return Result.success(result);
    }
}
