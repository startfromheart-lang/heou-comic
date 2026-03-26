package com.heou.comicgenerator.controller;

import com.heou.comicgenerator.common.Result;
import com.heou.comicgenerator.entity.ComicProject;
import com.heou.comicgenerator.service.ComicProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ComicProjectController {

    @Autowired
    private ComicProjectService projectService;

    @GetMapping("/list")
    public Result<List<ComicProject>> list(ComicProject project) {
        List<ComicProject> list = projectService.selectProjectList(project);
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result<ComicProject> getInfo(@PathVariable Integer id) {
        return Result.success(projectService.selectProjectById(id));
    }

    @PostMapping
    public Result add(@RequestBody ComicProject project) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 这里需要设置创建者ID，暂时简化为1
        project.setCreatorId(1);
        project.setStatus(0);
        return Result.success(projectService.insertProject(project));
    }

    @PutMapping
    public Result edit(@RequestBody ComicProject project) {
        return Result.success(projectService.updateProject(project));
    }

    @DeleteMapping("/{id}")
    public Result remove(@PathVariable Integer id) {
        return Result.success(projectService.deleteProjectById(id));
    }

    @PostMapping("/{id}/generate")
    public Result generateComic(@PathVariable Integer id) {
        // 这里实现漫画生成逻辑
        return Result.success("漫画生成任务已启动");
    }
}