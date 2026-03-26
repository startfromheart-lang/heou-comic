package com.heou.comic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heou.comic.entity.Scene;

public interface SceneService extends IService<Scene> {

    Page<Scene> getScenePage(Integer page, Integer size, String name);

    void createScene(Scene scene);

    void updateScene(Long id, Scene scene);

    void deleteScene(Long id);
}
