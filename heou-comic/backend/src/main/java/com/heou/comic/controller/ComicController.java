package com.heou.comic.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heou.comic.common.PageResult;
import com.heou.comic.common.Result;
import com.heou.comic.entity.Comic;
import com.heou.comic.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comic")
public class ComicController {

    @Autowired
    private ComicService comicService;

    @GetMapping("/list")
    public Result<PageResult<Comic>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String title) {
        Page<Comic> comicPage = comicService.getComicPage(page, size, title);
        return Result.success(new PageResult<>(comicPage.getTotal(), comicPage.getRecords()));
    }

    @GetMapping("/{id}")
    public Result<Comic> getById(@PathVariable Long id) {
        Comic comic = comicService.getById(id);
        return Result.success(comic);
    }

    @PostMapping
    public Result<Void> create(@RequestBody Comic comic) {
        comicService.save(comic);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        comicService.removeById(id);
        return Result.success();
    }
}
