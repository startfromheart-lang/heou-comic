package com.heou.comic.service.impl;

import com.heou.comic.entity.Topic;
import com.heou.comic.mapper.TopicMapper;
import com.heou.comic.service.TopicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicMapper topicMapper;

    public TopicServiceImpl(TopicMapper topicMapper) {
        this.topicMapper = topicMapper;
    }

    @Override
    public List<Topic> list(String topicTitle, Integer topicType, Integer status) {
        return topicMapper.selectList(topicTitle, topicType, status);
    }

    @Override
    public Topic getById(Long id) {
        return topicMapper.selectById(id);
    }

    @Override
    public boolean save(Topic topic) {
        return topicMapper.insert(topic) > 0;
    }

    @Override
    public boolean update(Topic topic) {
        return topicMapper.update(topic) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return topicMapper.deleteById(id) > 0;
    }

    @Override
    public List<Topic> getDailyHot() {
        return topicMapper.selectDailyHot();
    }

    @Override
    public List<Topic> getWeeklyHot() {
        return topicMapper.selectWeeklyHot();
    }
}
