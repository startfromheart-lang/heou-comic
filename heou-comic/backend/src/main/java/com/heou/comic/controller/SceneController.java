package com.heou.comic.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heou.comic.common.PageResult;
import com.heou.comic.common.Result;
import com.heou.comic.entity.Scene;
import com.heou.comic.service.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scene")
public class SceneController {

    @Autowired
    private SceneService sceneService;

    @GetMapping("/list")
    public Result<PageResult<Scene>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String name) {
        Page<Scene> scenePage = sceneService.getScenePage(page, size, name);
        return Result.success(new PageResult<>(scenePage.getTotal(), scenePage.getRecords()));
    }

    @PostMapping
    public Result<Void> create(@RequestBody Scene scene) {
        sceneService.createScene(scene);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody Scene scene) {
        sceneService.updateScene(id, scene);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        sceneService.deleteScene(id);
        return Result.success();
    }
}
