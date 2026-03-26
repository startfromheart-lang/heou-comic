package com.heou.comic.mapper;

import com.heou.comic.entity.SysConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysConfigMapper {
    List<SysConfig> selectList(@Param("configKey") String configKey, @Param("configName") String configName);

    SysConfig selectById(@Param("id") Long id);

    SysConfig selectByKey(@Param("configKey") String configKey);

    int insert(SysConfig config);

    int update(SysConfig config);

    int deleteById(@Param("id") Long id);
}
