package com.heou.comic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heou.comic.entity.Topic;
import com.heou.comic.mapper.TopicMapper;
import com.heou.comic.service.TopicService;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {

    @Override
    public Page<Topic> getTopicPage(Integer page, Integer size, String type) {
        Page<Topic> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<Topic> wrapper = new LambdaQueryWrapper<>();
        if (type != null && !type.isEmpty()) {
            wrapper.eq(Topic::getType, type);
        }
        wrapper.orderByDesc(Topic::getCreateTime);
        return page(pageParam, wrapper);
    }

    @Override
    public void createTopic(Topic topic) {
        save(topic);
    }

    @Override
    public void updateTopic(Long id, Topic topic) {
        topic.setId(id);
        updateById(topic);
    }
}
