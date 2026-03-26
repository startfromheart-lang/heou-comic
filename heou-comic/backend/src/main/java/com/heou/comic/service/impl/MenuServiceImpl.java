package com.heou.comic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heou.comic.entity.Menu;
import com.heou.comic.mapper.MenuMapper;
import com.heou.comic.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<Menu> getUserMenus(Long userId) {
        List<Menu> rootMenus = baseMapper.selectUserMenus(userId);
        for (Menu menu : rootMenus) {
            List<Menu> children = baseMapper.selectUserMenusByParentId(userId, menu.getId());
            menu.setChildren(children);
        }
        return rootMenus;
    }

    @Override
    public List<Menu> getMenuTree() {
        List<Menu> rootMenus = baseMapper.selectRootMenus();
        for (Menu menu : rootMenus) {
            buildMenuTree(menu);
        }
        return rootMenus;
    }

    private void buildMenuTree(Menu menu) {
        List<Menu> children = baseMapper.selectByParentId(menu.getId());
        if (!children.isEmpty()) {
            menu.setChildren(children);
            for (Menu child : children) {
                buildMenuTree(child);
            }
        }
    }

    @Override
    public void createMenu(Menu menu) {
        save(menu);
    }

    @Override
    public void updateMenu(Long id, Menu menu) {
        menu.setId(id);
        updateById(menu);
    }

    @Override
    public void deleteMenu(Long id) {
        // 检查是否有角色引用该菜单
        if (baseMapper.countRolesByMenuId(id) > 0) {
            throw new RuntimeException("该菜单已被角色引用，无法删除");
        }
        // 检查是否有子菜单
        List<Menu> children = baseMapper.selectByParentId(id);
        if (!children.isEmpty()) {
            throw new RuntimeException("该菜单下有子菜单，无法删除");
        }
        removeById(id);
    }
}
