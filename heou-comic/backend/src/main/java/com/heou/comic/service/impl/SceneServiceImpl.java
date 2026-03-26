package com.heou.comic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heou.comic.entity.Scene;
import com.heou.comic.mapper.SceneMapper;
import com.heou.comic.service.SceneService;
import org.springframework.stereotype.Service;

@Service
public class SceneServiceImpl extends ServiceImpl<SceneMapper, Scene> implements SceneService {

    @Override
    public Page<Scene> getScenePage(Integer page, Integer size, String name) {
        Page<Scene> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<Scene> wrapper = new LambdaQueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            wrapper.like(Scene::getName, name);
        }
        wrapper.orderByDesc(Scene::getCreateTime);
        return page(pageParam, wrapper);
    }

    @Override
    public void createScene(Scene scene) {
        save(scene);
    }

    @Override
    public void updateScene(Long id, Scene scene) {
        scene.setId(id);
        updateById(scene);
    }

    @Override
    public void deleteScene(Long id) {
        removeById(id);
    }
}
