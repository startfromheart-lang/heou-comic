package com.heou.comic.mapper;

import com.heou.comic.entity.Comic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ComicMapper {
    List<Comic> selectList(@Param("comicName") String comicName, @Param("status") Integer status);

    Comic selectById(@Param("id") Long id);

    int insert(Comic comic);

    int update(Comic comic);

    int deleteById(@Param("id") Long id);
}
