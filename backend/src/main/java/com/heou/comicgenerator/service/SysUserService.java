package com.heou.comicgenerator.service;

import com.heou.comicgenerator.entity.SysUser;
import java.util.List;

public interface SysUserService {
    List<SysUser> selectUserList(SysUser user);
    SysUser selectUserById(Integer id);
    SysUser selectUserByUsername(String username);
    int insertUser(SysUser user);
    int updateUser(SysUser user);
    int deleteUserById(Integer id);
    boolean checkUsernameUnique(SysUser user);
    boolean checkAdminUser(Integer id);
}