package com.heou.comicgenerator.mapper;

import com.heou.comicgenerator.entity.ComicProject;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ComicProjectMapper {
    List<ComicProject> selectProjectList(ComicProject project);
    ComicProject selectProjectById(Integer id);
    int insertProject(ComicProject project);
    int updateProject(ComicProject project);
    int deleteProjectById(Integer id);
    int countProjectByScriptId(Integer scriptId);
    int countProjectByStatus(Integer status);
}