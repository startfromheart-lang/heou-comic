package com.heou.comicgenerator.service;

import com.heou.comicgenerator.entity.ComicProject;
import java.util.List;

public interface ComicProjectService {
    List<ComicProject> selectProjectList(ComicProject project);
    ComicProject selectProjectById(Integer id);
    int insertProject(ComicProject project);
    int updateProject(ComicProject project);
    int deleteProjectById(Integer id);
    int countProjectByStatus(Integer status);
}