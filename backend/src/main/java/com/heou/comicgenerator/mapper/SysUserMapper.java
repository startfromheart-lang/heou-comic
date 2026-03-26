package com.heou.comicgenerator.mapper;

import com.heou.comicgenerator.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface SysUserMapper {
    List<SysUser> selectUserList(SysUser user);
    SysUser selectUserById(Integer id);
    SysUser selectUserByUsername(String username);
    int insertUser(SysUser user);
    int updateUser(SysUser user);
    int deleteUserById(Integer id);
    int countAdminUsers();
    List<Integer> selectRoleIdsByUserId(Integer userId);
    int insertUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
    int deleteUserRoleByUserId(Integer userId);
    int deleteUserRoleByRoleId(Integer roleId);
}