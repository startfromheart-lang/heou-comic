package com.heou.comic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heou.comic.entity.ComicTask;
import com.heou.comic.mapper.ComicTaskMapper;
import com.heou.comic.service.ComicTaskService;
import org.springframework.stereotype.Service;

@Service
public class ComicTaskServiceImpl extends ServiceImpl<ComicTaskMapper, ComicTask> implements ComicTaskService {

    @Override
    public Page<ComicTask> getTaskPage(Integer page, Integer size, String name) {
        Page<ComicTask> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<ComicTask> wrapper = new LambdaQueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            wrapper.like(ComicTask::getName, name);
        }
        wrapper.orderByDesc(ComicTask::getCreateTime);
        return page(pageParam, wrapper);
    }

    @Override
    public void createTask(ComicTask task) {
        task.setStatus(0);
        save(task);
    }

    @Override
    public void updateTask(Long id, ComicTask task) {
        task.setId(id);
        updateById(task);
    }

    @Override
    public void startTask(Long id) {
        ComicTask task = getById(id);
        if (task != null) {
            task.setStatus(1);
            updateById(task);
        }
    }

    @Override
    public void stopTask(Long id) {
        ComicTask task = getById(id);
        if (task != null) {
            task.setStatus(2);
            updateById(task);
        }
    }
}
