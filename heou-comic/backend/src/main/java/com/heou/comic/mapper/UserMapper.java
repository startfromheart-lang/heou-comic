package com.heou.comic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heou.comic.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT m.permission FROM sys_menu m " +
            "INNER JOIN sys_role_menu rm ON m.id = rm.menu_id " +
            "INNER JOIN sys_user_role ur ON rm.role_id = ur.role_id " +
            "WHERE ur.user_id = #{userId} AND m.permission IS NOT NULL")
    List<String> selectUserPermissions(@Param("userId") Long userId);

    @Select("SELECT r.* FROM sys_role r " +
            "INNER JOIN sys_user_role ur ON r.id = ur.role_id " +
            "WHERE ur.user_id = #{userId}")
    List<com.heou.comic.entity.Role> selectUserRoles(@Param("userId") Long userId);

    @Select("SELECT COUNT(*) FROM sys_user_role ur " +
            "INNER JOIN sys_role r ON ur.role_id = r.id " +
            "WHERE r.code = 'super_admin' AND ur.user_id = #{userId}")
    int isSuperAdmin(@Param("userId") Long userId);

    @Select("SELECT COUNT(*) FROM sys_user_role ur " +
            "INNER JOIN sys_role r ON ur.role_id = r.id " +
            "WHERE r.code = 'super_admin'")
    int countSuperAdmin();
}
