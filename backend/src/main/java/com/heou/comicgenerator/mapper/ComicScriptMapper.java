package com.heou.comicgenerator.mapper;

import com.heou.comicgenerator.entity.ComicScript;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ComicScriptMapper {
    List<ComicScript> selectScriptList(ComicScript script);
    ComicScript selectScriptById(Integer id);
    int insertScript(ComicScript script);
    int updateScript(ComicScript script);
    int deleteScriptById(Integer id);
    int countProjectByScriptId(Integer scriptId);
}