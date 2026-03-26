package com.heou.comic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heou.comic.entity.Menu;
import com.heou.comic.entity.Role;
import com.heou.comic.entity.RoleMenu;
import com.heou.comic.mapper.RoleMapper;
import com.heou.comic.mapper.RoleMenuMapper;
import com.heou.comic.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public Page<Role> getRolePage(Integer page, Integer size, String name) {
        Page<Role> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            wrapper.like(Role::getName, name);
        }
        wrapper.orderByDesc(Role::getCreateTime);
        return page(pageParam, wrapper);
    }

    @Override
    public void createRole(Role role) {
        save(role);
    }

    @Override
    public void updateRole(Long id, Role role) {
        role.setId(id);
        updateById(role);
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        // 检查是否有用户引用该角色
        if (baseMapper.countUsersByRoleId(id) > 0) {
            throw new RuntimeException("该角色已被用户引用，无法删除");
        }
        removeById(id);
        // 删除角色菜单关联
        roleMenuMapper.delete(new LambdaQueryWrapper<RoleMenu>()
                .eq(RoleMenu::getRoleId, id));
    }

    @Override
    public List<Menu> getRoleMenus(Long roleId) {
        return baseMapper.selectRoleMenus(roleId);
    }

    @Override
    @Transactional
    public void assignRoleMenus(Long roleId, List<Long> menuIds) {
        // 删除原有菜单
        roleMenuMapper.delete(new LambdaQueryWrapper<RoleMenu>()
                .eq(RoleMenu::getRoleId, roleId));
        // 添加新菜单
        if (menuIds != null && !menuIds.isEmpty()) {
            for (Long menuId : menuIds) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menuId);
                roleMenuMapper.insert(roleMenu);
            }
        }
    }
}
