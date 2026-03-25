package com.heou.comic.service.impl;

import com.heou.comic.entity.SysRole;
import com.heou.comic.exception.BusinessException;
import com.heou.comic.mapper.SysRoleMapper;
import com.heou.comic.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleMapper sysRoleMapper;

    public SysRoleServiceImpl(SysRoleMapper sysRoleMapper) {
        this.sysRoleMapper = sysRoleMapper;
    }

    @Override
    public List<SysRole> list(String roleName, String roleCode, Integer status) {
        return sysRoleMapper.selectList(roleName, roleCode, status);
    }

    @Override
    public SysRole getById(Long id) {
        return sysRoleMapper.selectById(id);
    }

    @Override
    public boolean save(SysRole role) {
        SysRole existRole = sysRoleMapper.selectByRoleCode(role.getRoleCode());
        if (existRole != null) {
            throw new BusinessException("角色编码已存在");
        }
        return sysRoleMapper.insert(role) > 0;
    }

    @Override
    public boolean update(SysRole role) {
        return sysRoleMapper.update(role) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        int userCount = sysRoleMapper.countUserByRoleId(id);
        if (userCount > 0) {
            throw new BusinessException("该角色已被用户引用，无法删除");
        }
        return sysRoleMapper.deleteById(id) > 0;
    }

    @Override
    public List<Long> getMenuIdsByRoleId(Long roleId) {
        return sysRoleMapper.selectMenuIdsByRoleId(roleId);
    }

    @Override
    public int countUserByRoleId(Long roleId) {
        return sysRoleMapper.countUserByRoleId(roleId);
    }
}
