package com.heou.comic.service.impl;

import com.heou.comic.entity.Task;
import com.heou.comic.mapper.TaskMapper;
import com.heou.comic.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public List<Task> list(String taskName, Integer taskType, Integer status) {
        return taskMapper.selectList(taskName, taskType, status);
    }

    @Override
    public Task getById(Long id) {
        return taskMapper.selectById(id);
    }

    @Override
    public boolean save(Task task) {
        return taskMapper.insert(task) > 0;
    }

    @Override
    public boolean update(Task task) {
        return taskMapper.update(task) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return taskMapper.deleteById(id) > 0;
    }

    @Override
    public int countRunning() {
        return taskMapper.countRunning();
    }

    @Override
    public int countByStatus(Integer status) {
        return taskMapper.countByStatus(status);
    }
}
