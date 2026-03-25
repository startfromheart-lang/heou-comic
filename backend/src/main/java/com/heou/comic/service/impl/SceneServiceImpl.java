package com.heou.comic.service.impl;

import com.heou.comic.entity.Scene;
import com.heou.comic.exception.BusinessException;
import com.heou.comic.mapper.ProjectMapper;
import com.heou.comic.mapper.SceneMapper;
import com.heou.comic.service.SceneService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SceneServiceImpl implements SceneService {

    private final SceneMapper sceneMapper;
    private final ProjectMapper projectMapper;

    public SceneServiceImpl(SceneMapper sceneMapper, ProjectMapper projectMapper) {
        this.sceneMapper = sceneMapper;
        this.projectMapper = projectMapper;
    }

    @Override
    public List<Scene> list(String sceneName, Integer status) {
        return sceneMapper.selectList(sceneName, status);
    }

    @Override
    public Scene getById(Long id) {
        return sceneMapper.selectById(id);
    }

    @Override
    public boolean save(Scene scene) {
        return sceneMapper.insert(scene) > 0;
    }

    @Override
    public boolean update(Scene scene) {
        return sceneMapper.update(scene) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        int projectCount = projectMapper.countBySceneId(id);
        if (projectCount > 0) {
            throw new BusinessException("该场景已被项目引用，无法删除");
        }
        return sceneMapper.deleteById(id) > 0;
    }
}
