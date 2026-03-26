package com.heou.comic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heou.comic.entity.Menu;
import com.heou.comic.entity.Role;

import java.util.List;

public interface RoleService extends IService<Role> {

    Page<Role> getRolePage(Integer page, Integer size, String name);

    void createRole(Role role);

    void updateRole(Long id, Role role);

    void deleteRole(Long id);

    List<Menu> getRoleMenus(Long roleId);

    void assignRoleMenus(Long roleId, List<Long> menuIds);
}
