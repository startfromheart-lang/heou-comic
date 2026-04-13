package com.heou.comic.service;

import com.heou.comic.common.LoginUser;
import com.heou.comic.entity.SysUser;

import java.util.List;

public interface SysUserService {
    List<SysUser> list(String username, String nickname, Integer status);

    SysUser getById(Long id);

    SysUser getByUsername(String username);

    boolean save(SysUser user);

    boolean update(SysUser user);

    boolean deleteById(Long id);

    LoginUser login(String username, String password);

    List<Long> getRoleIdsByUserId(Long userId);

    int countAdmin();

    int countByRoleId(Long roleId);

    int countByMenuId(Long menuId);
}
