package com.heou.comicgenerator.service.impl;

import com.heou.comicgenerator.entity.ComicScript;
import com.heou.comicgenerator.mapper.ComicProjectMapper;
import com.heou.comicgenerator.mapper.ComicScriptMapper;
import com.heou.comicgenerator.service.ComicScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComicScriptServiceImpl implements ComicScriptService {

    @Autowired
    private ComicScriptMapper scriptMapper;

    @Autowired
    private ComicProjectMapper projectMapper;

    @Override
    public List<ComicScript> selectScriptList(ComicScript script) {
        return scriptMapper.selectScriptList(script);
    }

    @Override
    public ComicScript selectScriptById(Integer id) {
        return scriptMapper.selectScriptById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertScript(ComicScript script) {
        return scriptMapper.insertScript(script);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateScript(ComicScript script) {
        return scriptMapper.updateScript(script);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteScriptById(Integer id) {
        return scriptMapper.deleteScriptById(id);
    }

    @Override
    public boolean checkScriptExistProject(Integer scriptId) {
        return projectMapper.countProjectByScriptId(scriptId) > 0;
    }
}