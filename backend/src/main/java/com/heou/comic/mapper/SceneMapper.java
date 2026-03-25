package com.heou.comic.mapper;

import com.heou.comic.entity.Scene;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SceneMapper {
    List<Scene> selectList(@Param("sceneName") String sceneName, @Param("status") Integer status);

    Scene selectById(@Param("id") Long id);

    int insert(Scene scene);

    int update(Scene scene);

    int deleteById(@Param("id") Long id);
}
