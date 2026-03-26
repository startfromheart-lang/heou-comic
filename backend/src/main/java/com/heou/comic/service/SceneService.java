package com.heou.comic.service;

import com.heou.comic.entity.Scene;

import java.util.List;

public interface SceneService {
    List<Scene> list(String sceneName, Integer status);

    Scene getById(Long id);

    boolean save(Scene scene);

    boolean update(Scene scene);

    boolean deleteById(Long id);
}
