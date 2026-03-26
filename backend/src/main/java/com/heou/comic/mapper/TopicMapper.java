package com.heou.comic.mapper;

import com.heou.comic.entity.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TopicMapper {
    List<Topic> selectList(@Param("topicTitle") String topicTitle, @Param("topicType") Integer topicType, @Param("status") Integer status);

    Topic selectById(@Param("id") Long id);

    int insert(Topic topic);

    int update(Topic topic);

    int deleteById(@Param("id") Long id);

    List<Topic> selectDailyHot();

    List<Topic> selectWeeklyHot();
}
