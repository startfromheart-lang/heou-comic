package com.heou.comic.service;

import com.heou.comic.entity.Topic;

import java.util.List;

public interface TopicService {
    List<Topic> list(String topicTitle, Integer topicType, Integer status);

    Topic getById(Long id);

    boolean save(Topic topic);

    boolean update(Topic topic);

    boolean deleteById(Long id);

    List<Topic> getDailyHot();

    List<Topic> getWeeklyHot();
}
