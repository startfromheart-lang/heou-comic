package com.heou.comic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heou.comic.entity.ComicTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ComicTaskMapper extends BaseMapper<ComicTask> {

    @Select("SELECT COUNT(*) FROM comic_task WHERE status = 1")
    int countRunningTasks();
}
