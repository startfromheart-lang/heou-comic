package com.heou.comicgenerator.service.impl;

import com.heou.comicgenerator.entity.ComicProject;
import com.heou.comicgenerator.mapper.ComicProjectMapper;
import com.heou.comicgenerator.service.ComicProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComicProjectServiceImpl implements ComicProjectService {

    @Autowired
    private ComicProjectMapper projectMapper;

    @Override
    public List<ComicProject> selectProjectList(ComicProject project) {
        return projectMapper.selectProjectList(project);
    }

    @Override
    public ComicProject selectProjectById(Integer id) {
        return projectMapper.selectProjectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertProject(ComicProject project) {
        return projectMapper.insertProject(project);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateProject(ComicProject project) {
        return projectMapper.updateProject(project);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteProjectById(Integer id) {
        return projectMapper.deleteProjectById(id);
    }

    @Override
    public int countProjectByStatus(Integer status) {
        return projectMapper.countProjectByStatus(status);
    }
}