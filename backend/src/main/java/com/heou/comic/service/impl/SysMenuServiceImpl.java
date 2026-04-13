package com.heou.comic.service.impl;

import com.heou.comic.entity.SysMenu;
import com.heou.comic.exception.BusinessException;
import com.heou.comic.mapper.SysMenuMapper;
import com.heou.comic.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    private final SysMenuMapper sysMenuMapper;

    public SysMenuServiceImpl(SysMenuMapper sysMenuMapper) {
        this.sysMenuMapper = sysMenuMapper;
    }

    @Override
    public List<SysMenu> list(String menuName, String menuCode, Integer status) {
        return sysMenuMapper.selectList(menuName, menuCode, status);
    }

    @Override
    public List<SysMenu> getByParentId(Long parentId) {
        return sysMenuMapper.selectByParentId(parentId);
    }

    @Override
    public SysMenu getById(Long id) {
        return sysMenuMapper.selectById(id);
    }

    @Override
    public boolean save(SysMenu menu) {
        return sysMenuMapper.insert(menu) > 0;
    }

    @Override
    public boolean update(SysMenu menu) {
        return sysMenuMapper.update(menu) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        int childCount = sysMenuMapper.countByParentId(id);
        if (childCount > 0) {
            throw new BusinessException("存在子菜单，无法删除");
        }
        int roleCount = sysMenuMapper.countByRoleId(id);
        if (roleCount > 0) {
            throw new BusinessException("该菜单已被角色引用，无法删除");
        }
        return sysMenuMapper.deleteById(id) > 0;
    }

    @Override
    public List<SysMenu> getByUserId(Long userId) {
        return sysMenuMapper.selectByUserId(userId);
    }

    @Override
    public int countByParentId(Long parentId) {
        return sysMenuMapper.countByParentId(parentId);
    }

    @Override
    public int countByRoleId(Long menuId) {
        return sysMenuMapper.countByRoleId(menuId);
    }
}
