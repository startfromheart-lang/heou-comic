package com.heou.comic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heou.comic.entity.Topic;

public interface TopicService extends IService<Topic> {

    Page<Topic> getTopicPage(Integer page, Integer size, String type);

    void createTopic(Topic topic);

    void updateTopic(Long id, Topic topic);
}
