package com.heou.comic.service.impl;

import com.heou.comic.entity.SysConfig;
import com.heou.comic.mapper.SysConfigMapper;
import com.heou.comic.service.SysConfigService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysConfigServiceImpl implements SysConfigService {

    private final SysConfigMapper sysConfigMapper;

    public SysConfigServiceImpl(SysConfigMapper sysConfigMapper) {
        this.sysConfigMapper = sysConfigMapper;
    }

    @Override
    public List<SysConfig> list(String configKey, String configName) {
        return sysConfigMapper.selectList(configKey, configName);
    }

    @Override
    public SysConfig getById(Long id) {
        return sysConfigMapper.selectById(id);
    }

    @Override
    public SysConfig getByKey(String configKey) {
        return sysConfigMapper.selectByKey(configKey);
    }

    @Override
    public boolean save(SysConfig config) {
        return sysConfigMapper.insert(config) > 0;
    }

    @Override
    public boolean update(SysConfig config) {
        return sysConfigMapper.update(config) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return sysConfigMapper.deleteById(id) > 0;
    }
}
