package com.heou.comic.controller;

import com.heou.comic.common.PageResult;
import com.heou.comic.common.Result;
import com.heou.comic.entity.Task;
import com.heou.comic.service.TaskService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/list")
    public Result<PageResult<Task>> list(
            @RequestParam(required = false) String taskName,
            @RequestParam(required = false) Integer taskType,
            @RequestParam(required = false) Integer status,
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size) {
        List<Task> list = taskService.list(taskName, taskType, status);
        int start = (int) ((current - 1) * size);
        int end = Math.min(start + size.intValue(), list.size());
        List<Task> pageList = list.subList(start, end);
        PageResult<Task> pageResult = PageResult.of(pageList, (long) list.size(), size, current);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result<Task> getById(@PathVariable Long id) {
        return Result.success(taskService.getById(id));
    }

    @PostMapping
    public Result<Void> save(@RequestBody Task task, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        task.setCreateUserId(userId);
        taskService.save(task);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(@RequestBody Task task) {
        taskService.update(task);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        taskService.deleteById(id);
        return Result.success();
    }

    @PutMapping("/{id}/stop")
    public Result<Void> stop(@PathVariable Long id) {
        Task task = taskService.getById(id);
        if (task != null) {
            task.setStatus(3);
            taskService.update(task);
        }
        return Result.success();
    }

    @PutMapping("/{id}/start")
    public Result<Void> start(@PathVariable Long id) {
        Task task = taskService.getById(id);
        if (task != null) {
            task.setStatus(1);
            taskService.update(task);
        }
        return Result.success();
    }
}
