package com.heou.comic.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heou.comic.common.PageResult;
import com.heou.comic.common.Result;
import com.heou.comic.entity.Script;
import com.heou.comic.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/script")
public class ScriptController {

    @Autowired
    private ScriptService scriptService;

    @GetMapping("/list")
    public Result<PageResult<Script>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String name) {
        Page<Script> scriptPage = scriptService.getScriptPage(page, size, name);
        return Result.success(new PageResult<>(scriptPage.getTotal(), scriptPage.getRecords()));
    }

    @PostMapping
    public Result<Void> create(@RequestBody Script script) {
        scriptService.createScript(script);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody Script script) {
        scriptService.updateScript(id, script);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        scriptService.deleteScript(id);
        return Result.success();
    }
}
