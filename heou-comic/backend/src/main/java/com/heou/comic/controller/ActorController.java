package com.heou.comic.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heou.comic.common.PageResult;
import com.heou.comic.common.Result;
import com.heou.comic.entity.Actor;
import com.heou.comic.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/list")
    public Result<PageResult<Actor>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String name) {
        Page<Actor> actorPage = actorService.getActorPage(page, size, name);
        return Result.success(new PageResult<>(actorPage.getTotal(), actorPage.getRecords()));
    }

    @PostMapping
    public Result<Void> create(@RequestBody Actor actor) {
        actorService.createActor(actor);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody Actor actor) {
        actorService.updateActor(id, actor);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        actorService.deleteActor(id);
        return Result.success();
    }
}
