package com.heou.comic.service.impl;

import com.heou.comic.common.LoginUser;
import com.heou.comic.entity.SysUser;
import com.heou.comic.exception.BusinessException;
import com.heou.comic.mapper.SysUserMapper;
import com.heou.comic.service.SysUserService;
import com.heou.comic.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    private final SysUserMapper sysUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public SysUserServiceImpl(SysUserMapper sysUserMapper, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.sysUserMapper = sysUserMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public List<SysUser> list(String username, String nickname, Integer status) {
        return sysUserMapper.selectList(username, nickname, status);
    }

    @Override
    public SysUser getById(Long id) {
        return sysUserMapper.selectById(id);
    }

    @Override
    public SysUser getByUsername(String username) {
        return sysUserMapper.selectByUsername(username);
    }

    @Override
    public boolean save(SysUser user) {
        SysUser existUser = sysUserMapper.selectByUsername(user.getUsername());
        if (existUser != null) {
            throw new BusinessException("用户名已存在");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return sysUserMapper.insert(user) > 0;
    }

    @Override
    public boolean update(SysUser user) {
        return sysUserMapper.update(user) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        SysUser user = sysUserMapper.selectById(id);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        if (user.getIsAdmin() == 1) {
            int adminCount = sysUserMapper.countAdmin();
            if (adminCount <= 1) {
                throw new BusinessException("不能删除最后一个超级管理员");
            }
        }
        return sysUserMapper.deleteById(id) > 0;
    }

    @Override
    public LoginUser login(String username, String password) {
        SysUser user = sysUserMapper.selectByUsername(username);
        if (user == null) {
            throw new BusinessException("用户名或密码错误");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BusinessException("用户名或密码错误");
        }
        if (user.getStatus() != 1) {
            throw new BusinessException("用户已被禁用");
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(user.getId());
        loginUser.setUsername(user.getUsername());
        loginUser.setNickname(user.getNickname());
        loginUser.setIsAdmin(user.getIsAdmin());
        loginUser.setToken(jwtUtil.generateToken(user.getId(), user.getUsername()));
        return loginUser;
    }

    @Override
    public List<Long> getRoleIdsByUserId(Long userId) {
        return sysUserMapper.selectRoleIdsByUserId(userId);
    }

    @Override
    public int countAdmin() {
        return sysUserMapper.countAdmin();
    }

    @Override
    public int countByRoleId(Long roleId) {
        return sysUserMapper.countByRoleId(roleId);
    }

    @Override
    public int countByMenuId(Long menuId) {
        return sysUserMapper.countByMenuId(menuId);
    }
}
