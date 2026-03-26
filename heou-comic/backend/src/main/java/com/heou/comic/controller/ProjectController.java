package com.heou.comic.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heou.comic.common.PageResult;
import com.heou.comic.common.Result;
import com.heou.comic.entity.Project;
import com.heou.comic.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/list")
    public Result<PageResult<Project>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String name) {
        Page<Project> projectPage = projectService.getProjectPage(page, size, name);
        return Result.success(new PageResult<>(projectPage.getTotal(), projectPage.getRecords()));
    }

    @PostMapping
    public Result<Void> create(@RequestBody Project project) {
        projectService.createProject(project);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody Project project) {
        projectService.updateProject(id, project);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        projectService.deleteProject(id);
        return Result.success();
    }

    @PostMapping("/{id}/generate")
    public Result<Void> generateComic(@PathVariable Long id) {
        // 触发漫画生成任务
        return Result.success();
    }
}
