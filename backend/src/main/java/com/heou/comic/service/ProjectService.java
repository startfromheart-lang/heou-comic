package com.heou.comic.service;

import com.heou.comic.entity.Project;

import java.util.List;

public interface ProjectService {
    List<Project> list(String projectName, Integer status);

    Project getById(Long id);

    boolean save(Project project);

    boolean update(Project project);

    boolean deleteById(Long id);

    int count();

    int countByStatus(Integer status);

    int countByScriptId(Long scriptId);

    int countByActorId(Long actorId);

    int countBySceneId(Long sceneId);
}
