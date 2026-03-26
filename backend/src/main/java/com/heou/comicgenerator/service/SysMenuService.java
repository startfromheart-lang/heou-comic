package com.heou.comicgenerator.service;

import com.heou.comicgenerator.entity.SysMenu;
import java.util.List;

public interface SysMenuService {
    List<SysMenu> selectMenuList(SysMenu menu);
    List<SysMenu> selectMenuTreeByUserId(Integer userId);
    SysMenu selectMenuById(Integer id);
    int insertMenu(SysMenu menu);
    int updateMenu(SysMenu menu);
    int deleteMenuById(Integer id);
    boolean checkMenuNameUnique(SysMenu menu);
    boolean hasChildByMenuId(Integer menuId);
    boolean checkMenuExistRole(Integer menuId);
}