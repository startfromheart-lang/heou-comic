package com.heou.comic.mapper;

import com.heou.comic.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper {
    List<Project> selectList(@Param("projectName") String projectName, @Param("status") Integer status);

    Project selectById(@Param("id") Long id);

    int insert(Project project);

    int update(Project project);

    int deleteById(@Param("id") Long id);

    int count();

    int countByStatus(@Param("status") Integer status);

    int countByScriptId(@Param("scriptId") Long scriptId);

    int countByActorId(@Param("actorId") Long actorId);

    int countBySceneId(@Param("sceneId") Long sceneId);
}
