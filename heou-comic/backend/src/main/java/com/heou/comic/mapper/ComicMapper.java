package com.heou.comic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heou.comic.entity.Comic;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ComicMapper extends BaseMapper<Comic> {
}
