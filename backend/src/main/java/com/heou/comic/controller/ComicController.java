package com.heou.comic.controller;

import com.heou.comic.common.PageResult;
import com.heou.comic.common.Result;
import com.heou.comic.entity.Comic;
import com.heou.comic.entity.ComicChapter;
import com.heou.comic.mapper.ComicChapterMapper;
import com.heou.comic.service.ComicService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comic")
public class ComicController {

    private final ComicService comicService;
    private final ComicChapterMapper comicChapterMapper;

    public ComicController(ComicService comicService, ComicChapterMapper comicChapterMapper) {
        this.comicService = comicService;
        this.comicChapterMapper = comicChapterMapper;
    }

    @GetMapping("/list")
    public Result<PageResult<Comic>> list(
            @RequestParam(required = false) String comicName,
            @RequestParam(required = false) Integer status,
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size) {
        List<Comic> list = comicService.list(comicName, status);
        int start = (int) ((current - 1) * size);
        int end = Math.min(start + size.intValue(), list.size());
        List<Comic> pageList = list.subList(start, end);
        PageResult<Comic> pageResult = PageResult.of(pageList, (long) list.size(), size, current);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result<Map<String, Object>> getById(@PathVariable Long id) {
        Comic comic = comicService.getById(id);
        List<ComicChapter> chapters = comicChapterMapper.selectByComicId(id);
        Map<String, Object> result = new HashMap<>();
        result.put("comic", comic);
        result.put("chapters", chapters);
        return Result.success(result);
    }

    @GetMapping("/chapter/{id}")
    public Result<ComicChapter> getChapterById(@PathVariable Long id) {
        return Result.success(comicChapterMapper.selectById(id));
    }

    @PostMapping
    public Result<Void> save(@RequestBody Comic comic, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        comic.setCreateUserId(userId);
        comicService.save(comic);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(@RequestBody Comic comic) {
        comicService.update(comic);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        comicService.deleteById(id);
        return Result.success();
    }
}
