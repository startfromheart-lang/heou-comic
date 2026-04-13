package com.heou.comic.service;

import com.heou.comic.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> list(String taskName, Integer taskType, Integer status);

    Task getById(Long id);

    boolean save(Task task);

    boolean update(Task task);

    boolean deleteById(Long id);

    int countRunning();

    int countByStatus(Integer status);
}
