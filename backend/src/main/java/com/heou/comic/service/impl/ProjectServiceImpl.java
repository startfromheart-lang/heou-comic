package com.heou.comic.service.impl;

import com.heou.comic.entity.Project;
import com.heou.comic.mapper.ProjectMapper;
import com.heou.comic.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @Override
    public List<Project> list(String projectName, Integer status) {
        return projectMapper.selectList(projectName, status);
    }

    @Override
    public Project getById(Long id) {
        return projectMapper.selectById(id);
    }

    @Override
    public boolean save(Project project) {
        return projectMapper.insert(project) > 0;
    }

    @Override
    public boolean update(Project project) {
        return projectMapper.update(project) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return projectMapper.deleteById(id) > 0;
    }

    @Override
    public int count() {
        return projectMapper.count();
    }

    @Override
    public int countByStatus(Integer status) {
        return projectMapper.countByStatus(status);
    }

    @Override
    public int countByScriptId(Long scriptId) {
        return projectMapper.countByScriptId(scriptId);
    }

    @Override
    public int countByActorId(Long actorId) {
        return projectMapper.countByActorId(actorId);
    }

    @Override
    public int countBySceneId(Long sceneId) {
        return projectMapper.countBySceneId(sceneId);
    }
}
