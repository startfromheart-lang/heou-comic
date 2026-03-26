package com.heou.comic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heou.comic.entity.Role;
import com.heou.comic.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

    User login(String username, String password);

    Page<User> getUserPage(Integer page, Integer size, String username, Integer status);

    void createUser(User user);

    void updateUser(Long id, User user);

    void deleteUser(Long id);

    List<Role> getUserRoles(Long userId);

    void assignUserRoles(Long userId, List<Long> roleIds);
}
