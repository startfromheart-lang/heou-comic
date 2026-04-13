package com.heou.comic.mapper;

import com.heou.comic.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper {
    List<SysUser> selectList(@Param("username") String username, @Param("nickname") String nickname, @Param("status") Integer status);

    SysUser selectById(@Param("id") Long id);

    SysUser selectByUsername(@Param("username") String username);

    int insert(SysUser user);

    int update(SysUser user);

    int deleteById(@Param("id") Long id);

    int countAdmin();

    List<Long> selectRoleIdsByUserId(@Param("userId") Long userId);

    int countByRoleId(@Param("roleId") Long roleId);

    int countByMenuId(@Param("menuId") Long menuId);
}
