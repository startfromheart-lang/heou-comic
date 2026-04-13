package com.heou.comic.service;

import com.heou.comic.entity.SysRole;

import java.util.List;

public interface SysRoleService {
    List<SysRole> list(String roleName, String roleCode, Integer status);

    SysRole getById(Long id);

    boolean save(SysRole role);

    boolean update(SysRole role);

    boolean deleteById(Long id);

    List<Long> getMenuIdsByRoleId(Long roleId);

    int countUserByRoleId(Long roleId);
}
