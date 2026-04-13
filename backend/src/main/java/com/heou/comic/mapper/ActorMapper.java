package com.heou.comic.mapper;

import com.heou.comic.entity.Actor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActorMapper {
    List<Actor> selectList(@Param("actorName") String actorName, @Param("status") Integer status);

    Actor selectById(@Param("id") Long id);

    int insert(Actor actor);

    int update(Actor actor);

    int deleteById(@Param("id") Long id);
}
