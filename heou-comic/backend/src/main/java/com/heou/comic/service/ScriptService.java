package com.heou.comic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heou.comic.entity.Script;

public interface ScriptService extends IService<Script> {

    Page<Script> getScriptPage(Integer page, Integer size, String name);

    void createScript(Script script);

    void updateScript(Long id, Script script);

    void deleteScript(Long id);
}
