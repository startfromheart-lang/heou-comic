package com.heou.comic.mapper;

import com.heou.comic.entity.ComicChapter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ComicChapterMapper {
    List<ComicChapter> selectByComicId(@Param("comicId") Long comicId);

    ComicChapter selectById(@Param("id") Long id);

    int insert(ComicChapter chapter);

    int update(ComicChapter chapter);

    int deleteById(@Param("id") Long id);
}
