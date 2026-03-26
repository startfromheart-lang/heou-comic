package com.heou.comicgenerator.mapper;

import com.heou.comicgenerator.entity.ComicScene;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ComicSceneMapper {
    List<ComicScene> selectSceneList(ComicScene scene);
    ComicScene selectSceneById(Integer id);
    int insertScene(ComicScene scene);
    int updateScene(ComicScene scene);
    int deleteSceneById(Integer id);
    int countProjectBySceneId(Integer sceneId);
}