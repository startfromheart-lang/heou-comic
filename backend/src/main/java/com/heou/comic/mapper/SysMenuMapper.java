package com.heou.comic.mapper;

import com.heou.comic.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysMenuMapper {
    List<SysMenu> selectList(@Param("menuName") String menuName, @Param("menuCode") String menuCode, @Param("status") Integer status);

    List<SysMenu> selectByParentId(@Param("parentId") Long parentId);

    SysMenu selectById(@Param("id") Long id);

    int insert(SysMenu menu);

    int update(SysMenu menu);

    int deleteById(@Param("id") Long id);

    List<SysMenu> selectByUserId(@Param("userId") Long userId);

    int countByParentId(@Param("parentId") Long parentId);

    int countByRoleId(@Param("roleId") Long roleId);
}
