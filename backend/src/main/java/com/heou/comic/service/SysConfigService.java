package com.heou.comic.service;

import com.heou.comic.entity.SysConfig;

import java.util.List;

public interface SysConfigService {
    List<SysConfig> list(String configKey, String configName);

    SysConfig getById(Long id);

    SysConfig getByKey(String configKey);

    boolean save(SysConfig config);

    boolean update(SysConfig config);

    boolean deleteById(Long id);
}
