package com.heou.comic.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heou.comic.common.PageResult;
import com.heou.comic.common.Result;
import com.heou.comic.entity.ComicTask;
import com.heou.comic.service.ComicTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comic-task")
public class ComicTaskController {

    @Autowired
    private ComicTaskService comicTaskService;

    @GetMapping("/list")
    public Result<PageResult<ComicTask>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String name) {
        Page<ComicTask> taskPage = comicTaskService.getTaskPage(page, size, name);
        return Result.success(new PageResult<>(taskPage.getTotal(), taskPage.getRecords()));
    }

    @PostMapping
    public Result<Void> create(@RequestBody ComicTask task) {
        comicTaskService.createTask(task);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody ComicTask task) {
        comicTaskService.updateTask(id, task);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        comicTaskService.removeById(id);
        return Result.success();
    }

    @PostMapping("/{id}/start")
    public Result<Void> startTask(@PathVariable Long id) {
        comicTaskService.startTask(id);
        return Result.success();
    }

    @PostMapping("/{id}/stop")
    public Result<Void> stopTask(@PathVariable Long id) {
        comicTaskService.stopTask(id);
        return Result.success();
    }
}
