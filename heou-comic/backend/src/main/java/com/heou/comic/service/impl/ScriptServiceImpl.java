package com.heou.comic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heou.comic.entity.Script;
import com.heou.comic.mapper.ProjectMapper;
import com.heou.comic.mapper.ScriptMapper;
import com.heou.comic.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScriptServiceImpl extends ServiceImpl<ScriptMapper, Script> implements ScriptService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public Page<Script> getScriptPage(Integer page, Integer size, String name) {
        Page<Script> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<Script> wrapper = new LambdaQueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            wrapper.like(Script::getName, name);
        }
        wrapper.orderByDesc(Script::getCreateTime);
        return page(pageParam, wrapper);
    }

    @Override
    public void createScript(Script script) {
        save(script);
    }

    @Override
    public void updateScript(Long id, Script script) {
        script.setId(id);
        updateById(script);
    }

    @Override
    public void deleteScript(Long id) {
        // 检查是否被项目引用
        if (projectMapper.countProjectsByScriptId(id) > 0) {
            throw new RuntimeException("该剧本已被项目引用，无法删除");
        }
        removeById(id);
    }
}
