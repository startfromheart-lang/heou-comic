package com.heou.comic.controller;

import com.heou.comic.common.PageResult;
import com.heou.comic.common.Result;
import com.heou.comic.entity.Actor;
import com.heou.comic.service.ActorService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actor")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/list")
    public Result<PageResult<Actor>> list(
            @RequestParam(required = false) String actorName,
            @RequestParam(required = false) Integer status,
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size) {
        List<Actor> list = actorService.list(actorName, status);
        int start = (int) ((current - 1) * size);
        int end = Math.min(start + size.intValue(), list.size());
        List<Actor> pageList = list.subList(start, end);
        PageResult<Actor> pageResult = PageResult.of(pageList, (long) list.size(), size, current);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result<Actor> getById(@PathVariable Long id) {
        return Result.success(actorService.getById(id));
    }

    @PostMapping
    public Result<Void> save(@RequestBody Actor actor, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        actor.setCreateUserId(userId);
        actorService.save(actor);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(@RequestBody Actor actor) {
        actorService.update(actor);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        actorService.deleteById(id);
        return Result.success();
    }
}
