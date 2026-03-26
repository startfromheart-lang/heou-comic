package com.heou.comicgenerator.controller;

import com.heou.comicgenerator.common.Result;
import com.heou.comicgenerator.entity.ComicScript;
import com.heou.comicgenerator.service.ComicScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/script")
public class ComicScriptController {

    @Autowired
    private ComicScriptService scriptService;

    @GetMapping("/list")
    public Result<List<ComicScript>> list(ComicScript script) {
        List<ComicScript> list = scriptService.selectScriptList(script);
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result<ComicScript> getInfo(@PathVariable Integer id) {
        return Result.success(scriptService.selectScriptById(id));
    }

    @PostMapping
    public Result add(@RequestBody ComicScript script) {
        script.setCreatorId(1);
        script.setStatus(1);
        return Result.success(scriptService.insertScript(script));
    }

    @PutMapping
    public Result edit(@RequestBody ComicScript script) {
        return Result.success(scriptService.updateScript(script));
    }

    @DeleteMapping("/{id}")
    public Result remove(@PathVariable Integer id) {
        if (scriptService.checkScriptExistProject(id)) {
            return Result.error("剧本已被项目引用,不允许删除");
        }
        return Result.success(scriptService.deleteScriptById(id));
    }
}