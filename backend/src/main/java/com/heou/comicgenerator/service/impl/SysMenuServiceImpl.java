package com.heou.comicgenerator.service.impl;

import com.heou.comicgenerator.entity.SysMenu;
import com.heou.comicgenerator.mapper.SysMenuMapper;
import com.heou.comicgenerator.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public List<SysMenu> selectMenuList(SysMenu menu) {
        return menuMapper.selectMenuList(menu);
    }

    @Override
    public List<SysMenu> selectMenuTreeByUserId(Integer userId) {
        List<SysMenu> menus = menuMapper.selectMenuByUserId(userId);
        return buildMenuTree(menus);
    }

    @Override
    public SysMenu selectMenuById(Integer id) {
        return menuMapper.selectMenuById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertMenu(SysMenu menu) {
        return menuMapper.insertMenu(menu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateMenu(SysMenu menu) {
        return menuMapper.updateMenu(menu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteMenuById(Integer id) {
        menuMapper.deleteRoleMenuByMenuId(id);
        return menuMapper.deleteMenuById(id);
    }

    @Override
    public boolean checkMenuNameUnique(SysMenu menu) {
        List<SysMenu> menus = menuMapper.selectMenuList(new SysMenu());
        for (SysMenu m : menus) {
            if (m.getMenuName().equals(menu.getMenuName()) && !m.getId().equals(menu.getId())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean hasChildByMenuId(Integer menuId) {
        return menuMapper.hasChildByMenuId(menuId);
    }

    @Override
    public boolean checkMenuExistRole(Integer menuId) {
        return menuMapper.countRoleMenuByMenuId(menuId) > 0;
    }

    private List<SysMenu> buildMenuTree(List<SysMenu> menus) {
        List<SysMenu> returnList = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        for (SysMenu menu : menus) {
            tempList.add(menu.getId());
        }
        for (SysMenu menu : menus) {
            // 如果是根节点（parentId为0或null，或者parentId不在菜单列表中）
            if (menu.getParentId() == null || menu.getParentId() == 0 || !tempList.contains(menu.getParentId())) {
                recursionFn(menus, menu, new ArrayList<>());
                returnList.add(menu);
            }
        }
        if (returnList.isEmpty()) {
            returnList = menus;
        }
        return returnList;
    }

    private void recursionFn(List<SysMenu> list, SysMenu menu, List<Integer> visited) {
        // 防止环形引用导致的死循环
        if (visited.contains(menu.getId())) {
            return;
        }
        visited.add(menu.getId());
        
        List<SysMenu> childList = getChildList(list, menu);
        menu.setChildren(childList);
        for (SysMenu child : childList) {
            if (hasChild(list, child)) {
                recursionFn(list, child, new ArrayList<>(visited));
            }
        }
    }

    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu menu) {
        return list.stream()
                .filter(m -> m.getParentId() != null && m.getParentId().equals(menu.getId()))
                .collect(Collectors.toList());
    }

    private boolean hasChild(List<SysMenu> list, SysMenu menu) {
        return list.stream()
                .anyMatch(m -> m.getParentId() != null && m.getParentId().equals(menu.getId()));
    }
}