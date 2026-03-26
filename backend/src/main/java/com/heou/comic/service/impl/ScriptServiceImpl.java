package com.heou.comic.service.impl;

import com.heou.comic.entity.Script;
import com.heou.comic.exception.BusinessException;
import com.heou.comic.mapper.ProjectMapper;
import com.heou.comic.mapper.ScriptMapper;
import com.heou.comic.service.ScriptService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScriptServiceImpl implements ScriptService {

    private final ScriptMapper scriptMapper;
    private final ProjectMapper projectMapper;

    public ScriptServiceImpl(ScriptMapper scriptMapper, ProjectMapper projectMapper) {
        this.scriptMapper = scriptMapper;
        this.projectMapper = projectMapper;
    }

    @Override
    public List<Script> list(String scriptName, Integer status) {
        return scriptMapper.selectList(scriptName, status);
    }

    @Override
    public Script getById(Long id) {
        return scriptMapper.selectById(id);
    }

    @Override
    public boolean save(Script script) {
        return scriptMapper.insert(script) > 0;
    }

    @Override
    public boolean update(Script script) {
        return scriptMapper.update(script) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        int projectCount = projectMapper.countByScriptId(id);
        if (projectCount > 0) {
            throw new BusinessException("该剧本已被项目引用，无法删除");
        }
        return scriptMapper.deleteById(id) > 0;
    }
}
