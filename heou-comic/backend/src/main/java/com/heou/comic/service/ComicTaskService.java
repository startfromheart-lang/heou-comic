package com.heou.comic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heou.comic.entity.ComicTask;

public interface ComicTaskService extends IService<ComicTask> {

    Page<ComicTask> getTaskPage(Integer page, Integer size, String name);

    void createTask(ComicTask task);

    void updateTask(Long id, ComicTask task);

    void startTask(Long id);

    void stopTask(Long id);
}
