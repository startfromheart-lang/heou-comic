package com.heou.comic.service;

import com.heou.comic.entity.SysMenu;

import java.util.List;

public interface SysMenuService {
    List<SysMenu> list(String menuName, String menuCode, Integer status);

    List<SysMenu> getByParentId(Long parentId);

    SysMenu getById(Long id);

    boolean save(SysMenu menu);

    boolean update(SysMenu menu);

    boolean deleteById(Long id);

    List<SysMenu> getByUserId(Long userId);

    int countByParentId(Long parentId);

    int countByRoleId(Long menuId);
}
