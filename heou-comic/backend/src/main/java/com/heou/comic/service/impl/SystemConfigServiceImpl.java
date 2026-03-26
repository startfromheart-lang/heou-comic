package com.heou.comic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heou.comic.entity.SystemConfig;
import com.heou.comic.mapper.SystemConfigMapper;
import com.heou.comic.service.SystemConfigService;
import org.springframework.stereotype.Service;

@Service
public class SystemConfigServiceImpl extends ServiceImpl<SystemConfigMapper, SystemConfig> implements SystemConfigService {
}
