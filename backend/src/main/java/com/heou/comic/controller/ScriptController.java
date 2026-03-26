package com.heou.comic.controller;

import com.heou.comic.common.PageResult;
import com.heou.comic.common.Result;
import com.heou.comic.entity.Script;
import com.heou.comic.service.ScriptService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/script")
public class ScriptController {

    private final ScriptService scriptService;

    public ScriptController(ScriptService scriptService) {
        this.scriptService = scriptService;
    }

    @GetMapping("/list")
    public Result<PageResult<Script>> list(
            @RequestParam(required = false) String scriptName,
            @RequestParam(required = false) Integer status,
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size) {
        List<Script> list = scriptService.list(scriptName, status);
        int start = (int) ((current - 1) * size);
        int end = Math.min(start + size.intValue(), list.size());
        List<Script> pageList = list.subList(start, end);
        PageResult<Script> pageResult = PageResult.of(pageList, (long) list.size(), size, current);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result<Script> getById(@PathVariable Long id) {
        return Result.success(scriptService.getById(id));
    }

    @PostMapping
    public Result<Void> save(@RequestBody Script script, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        script.setCreateUserId(userId);
        scriptService.save(script);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(@RequestBody Script script) {
        scriptService.update(script);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        scriptService.deleteById(id);
        return Result.success();
    }
}
