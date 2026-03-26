package com.heou.comic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heou.comic.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    @Select("SELECT m.* FROM sys_menu m " +
            "INNER JOIN sys_role_menu rm ON m.id = rm.menu_id " +
            "INNER JOIN sys_user_role ur ON rm.role_id = ur.role_id " +
            "WHERE ur.user_id = #{userId} AND m.parent_id IS NULL " +
            "ORDER BY m.sort")
    List<Menu> selectUserMenus(@Param("userId") Long userId);

    @Select("SELECT m.* FROM sys_menu m " +
            "INNER JOIN sys_role_menu rm ON m.id = rm.menu_id " +
            "INNER JOIN sys_user_role ur ON rm.role_id = ur.role_id " +
            "WHERE ur.user_id = #{userId} AND m.parent_id = #{parentId} " +
            "ORDER BY m.sort")
    List<Menu> selectUserMenusByParentId(@Param("userId") Long userId, @Param("parentId") Long parentId);

    @Select("SELECT COUNT(*) FROM sys_role_menu WHERE menu_id = #{menuId}")
    int countRolesByMenuId(@Param("menuId") Long menuId);

    @Select("SELECT * FROM sys_menu WHERE parent_id = #{parentId} ORDER BY sort")
    List<Menu> selectByParentId(@Param("parentId") Long parentId);

    @Select("SELECT * FROM sys_menu WHERE parent_id IS NULL ORDER BY sort")
    List<Menu> selectRootMenus();
}
