package com.heou.comic.service;

import com.heou.comic.entity.Script;

import java.util.List;

public interface ScriptService {
    List<Script> list(String scriptName, Integer status);

    Script getById(Long id);

    boolean save(Script script);

    boolean update(Script script);

    boolean deleteById(Long id);
}
