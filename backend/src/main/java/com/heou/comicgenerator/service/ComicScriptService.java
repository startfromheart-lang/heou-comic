package com.heou.comicgenerator.service;

import com.heou.comicgenerator.entity.ComicScript;
import java.util.List;

public interface ComicScriptService {
    List<ComicScript> selectScriptList(ComicScript script);
    ComicScript selectScriptById(Integer id);
    int insertScript(ComicScript script);
    int updateScript(ComicScript script);
    int deleteScriptById(Integer id);
    boolean checkScriptExistProject(Integer scriptId);
}