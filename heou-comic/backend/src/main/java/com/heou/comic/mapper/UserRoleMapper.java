package com.heou.comic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heou.comic.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    @Select("SELECT COUNT(*) FROM sys_user_role WHERE role_id = #{roleId}")
    int countByRoleId(@Param("roleId") Long roleId);
}
