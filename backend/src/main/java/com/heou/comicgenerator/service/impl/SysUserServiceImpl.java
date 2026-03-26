package com.heou.comicgenerator.service.impl;

import com.heou.comicgenerator.entity.SysUser;
import com.heou.comicgenerator.mapper.SysUserMapper;
import com.heou.comicgenerator.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<SysUser> selectUserList(SysUser user) {
        return userMapper.selectUserList(user);
    }

    @Override
    public SysUser selectUserById(Integer id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public SysUser selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertUser(SysUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        int rows = userMapper.insertUser(user);
        if (user.getRoleIds() != null && user.getRoleIds().size() > 0) {
            for (Integer roleId : user.getRoleIds()) {
                userMapper.insertUserRole(user.getId(), roleId);
            }
        }
        return rows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(SysUser user) {
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userMapper.deleteUserRoleByUserId(user.getId());
        if (user.getRoleIds() != null && !user.getRoleIds().isEmpty()) {
            for (Integer roleId : user.getRoleIds()) {
                userMapper.insertUserRole(user.getId(), roleId);
            }
        }
        return userMapper.updateUser(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteUserById(Integer id) {
        userMapper.deleteUserRoleByUserId(id);
        return userMapper.deleteUserById(id);
    }

    @Override
    public boolean checkUsernameUnique(SysUser user) {
        SysUser existUser = userMapper.selectUserByUsername(user.getUsername());
        return existUser == null || existUser.getId().equals(user.getId());
    }

    @Override
    public boolean checkAdminUser(Integer id) {
        List<Integer> roleIds = userMapper.selectRoleIdsByUserId(id);
        for (Integer roleId : roleIds) {
            if (roleId == 1) {
                int adminCount = userMapper.countAdminUsers();
                if (adminCount <= 1) {
                    return true;
                }
                break;
            }
        }
        return false;
    }
}