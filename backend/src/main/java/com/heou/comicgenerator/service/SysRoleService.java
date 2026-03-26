package com.heou.comicgenerator.service;

import com.heou.comicgenerator.entity.SysRole;
import java.util.List;

public interface SysRoleService {
    List<SysRole> selectRoleList(SysRole role);
    SysRole selectRoleById(Integer id);
    int insertRole(SysRole role);
    int updateRole(SysRole role);
    int deleteRoleById(Integer id);
    boolean checkRoleNameUnique(SysRole role);
    boolean checkRoleCodeUnique(SysRole role);
    boolean countUserByRoleId(Integer roleId);
}