package com.heou.comic.mapper;

import com.heou.comic.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMapper {
    List<SysRole> selectList(@Param("roleName") String roleName, @Param("roleCode") String roleCode, @Param("status") Integer status);

    SysRole selectById(@Param("id") Long id);

    SysRole selectByRoleCode(@Param("roleCode") String roleCode);

    int insert(SysRole role);

    int update(SysRole role);

    int deleteById(@Param("id") Long id);

    List<Long> selectMenuIdsByRoleId(@Param("roleId") Long roleId);

    int countUserByRoleId(@Param("roleId") Long roleId);
}
