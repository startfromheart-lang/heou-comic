package com.heou.comic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heou.comic.entity.Project;
import com.heou.comic.mapper.ProjectMapper;
import com.heou.comic.service.ProjectService;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Override
    public Page<Project> getProjectPage(Integer page, Integer size, String name) {
        Page<Project> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<Project> wrapper = new LambdaQueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            wrapper.like(Project::getName, name);
        }
        wrapper.orderByDesc(Project::getCreateTime);
        return page(pageParam, wrapper);
    }

    @Override
    public void createProject(Project project) {
        project.setStatus(0);
        save(project);
    }

    @Override
    public void updateProject(Long id, Project project) {
        project.setId(id);
        updateById(project);
    }

    @Override
    public void deleteProject(Long id) {
        removeById(id);
    }
}
