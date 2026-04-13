package com.heou.comic.controller;

import com.heou.comic.common.PageResult;
import com.heou.comic.common.Result;
import com.heou.comic.entity.Scene;
import com.heou.comic.service.SceneService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scene")
public class SceneController {

    private final SceneService sceneService;

    public SceneController(SceneService sceneService) {
        this.sceneService = sceneService;
    }

    @GetMapping("/list")
    public Result<PageResult<Scene>> list(
            @RequestParam(required = false) String sceneName,
            @RequestParam(required = false) Integer status,
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size) {
        List<Scene> list = sceneService.list(sceneName, status);
        int start = (int) ((current - 1) * size);
        int end = Math.min(start + size.intValue(), list.size());
        List<Scene> pageList = list.subList(start, end);
        PageResult<Scene> pageResult = PageResult.of(pageList, (long) list.size(), size, current);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result<Scene> getById(@PathVariable Long id) {
        return Result.success(sceneService.getById(id));
    }

    @PostMapping
    public Result<Void> save(@RequestBody Scene scene, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        scene.setCreateUserId(userId);
        sceneService.save(scene);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(@RequestBody Scene scene) {
        sceneService.update(scene);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        sceneService.deleteById(id);
        return Result.success();
    }
}
