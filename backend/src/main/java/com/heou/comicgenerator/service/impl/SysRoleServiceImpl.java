package com.heou.comicgenerator.service.impl;

import com.heou.comicgenerator.entity.SysRole;
import com.heou.comicgenerator.mapper.SysRoleMapper;
import com.heou.comicgenerator.mapper.SysUserMapper;
import com.heou.comicgenerator.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public List<SysRole> selectRoleList(SysRole role) {
        return roleMapper.selectRoleList(role);
    }

    @Override
    public SysRole selectRoleById(Integer id) {
        SysRole role = roleMapper.selectRoleById(id);
        role.setMenuIds(roleMapper.selectMenuIdsByRoleId(id));
        return role;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertRole(SysRole role) {
        int rows = roleMapper.insertRole(role);
        if (role.getMenuIds() != null && !role.getMenuIds().isEmpty()) {
            for (Integer menuId : role.getMenuIds()) {
                roleMapper.insertRoleMenu(role.getId(), menuId);
            }
        }
        return rows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateRole(SysRole role) {
        roleMapper.deleteRoleMenuByRoleId(role.getId());
        if (role.getMenuIds() != null && !role.getMenuIds().isEmpty()) {
            for (Integer menuId : role.getMenuIds()) {
                roleMapper.insertRoleMenu(role.getId(), menuId);
            }
        }
        return roleMapper.updateRole(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteRoleById(Integer id) {
        roleMapper.deleteRoleMenuByRoleId(id);
        userMapper.deleteUserRoleByRoleId(id);
        return roleMapper.deleteRoleById(id);
    }

    @Override
    public boolean checkRoleNameUnique(SysRole role) {
        SysRole existRole = roleMapper.selectRoleByCode(role.getRoleName());
        return existRole == null || existRole.getId().equals(role.getId());
    }

    @Override
    public boolean checkRoleCodeUnique(SysRole role) {
        SysRole existRole = roleMapper.selectRoleByCode(role.getRoleCode());
        return existRole == null || existRole.getId().equals(role.getId());
    }

    @Override
    public boolean countUserByRoleId(Integer roleId) {
        return roleMapper.countUserByRoleId(roleId) > 0;
    }
}