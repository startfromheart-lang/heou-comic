package com.heou.comic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heou.comic.entity.Menu;

import java.util.List;

public interface MenuService extends IService<Menu> {

    List<Menu> getUserMenus(Long userId);

    List<Menu> getMenuTree();

    void createMenu(Menu menu);

    void updateMenu(Long id, Menu menu);

    void deleteMenu(Long id);
}
