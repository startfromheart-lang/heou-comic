package com.heou.comicgenerator.mapper;

import com.heou.comicgenerator.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SysMenuMapper {
    List<SysMenu> selectMenuList(SysMenu menu);
    SysMenu selectMenuById(Integer id);
    List<SysMenu> selectMenuByUserId(Integer userId);
    List<String> selectPermsByUserId(Integer userId);
    int insertMenu(SysMenu menu);
    int updateMenu(SysMenu menu);
    int deleteMenuById(Integer id);
    int countRoleMenuByMenuId(Integer menuId);
    boolean hasChildByMenuId(Integer menuId);
    int deleteRoleMenuByMenuId(Integer menuId);
}