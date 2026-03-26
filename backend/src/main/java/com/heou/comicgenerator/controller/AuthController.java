package com.heou.comicgenerator.controller;

import com.heou.comicgenerator.common.JwtUtil;
import com.heou.comicgenerator.common.LoginRequest;
import com.heou.comicgenerator.common.LoginResponse;
import com.heou.comicgenerator.common.Result;
import com.heou.comicgenerator.entity.SysUser;
import com.heou.comicgenerator.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SysUserMapper userMapper;

    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtil.generateToken(request.getUsername());
            SysUser user = userMapper.selectUserByUsername(request.getUsername());
            return Result.success(new LoginResponse(token, user.getUsername(), user.getRealName()));
        } catch (Exception e) {
            return Result.error("用户名或密码错误");
        }
    }

    @GetMapping("/info")
    public Result<SysUser> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        SysUser user = userMapper.selectUserByUsername(username);
        user.setPassword(null);
        return Result.success(user);
    }
}