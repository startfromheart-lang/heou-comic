package com.heou.comicgenerator.mapper;

import com.heou.comicgenerator.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface SysRoleMapper {
    List<SysRole> selectRoleList(SysRole role);
    SysRole selectRoleById(Integer id);
    SysRole selectRoleByCode(String roleCode);
    int insertRole(SysRole role);
    int updateRole(SysRole role);
    int deleteRoleById(Integer id);
    int countUserByRoleId(Integer roleId);
    List<Integer> selectMenuIdsByRoleId(Integer roleId);
    int insertRoleMenu(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);
    int deleteRoleMenuByRoleId(Integer roleId);
    int deleteRoleMenuByMenuId(Integer menuId);
}