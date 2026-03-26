package com.heou.comic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heou.comic.entity.Project;

public interface ProjectService extends IService<Project> {

    Page<Project> getProjectPage(Integer page, Integer size, String name);

    void createProject(Project project);

    void updateProject(Long id, Project project);

    void deleteProject(Long id);
}
