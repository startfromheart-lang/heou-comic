package com.heou.comic.controller;

import com.heou.comic.common.Result;
import com.heou.comic.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ScriptMapper scriptMapper;

    @Autowired
    private ComicMapper comicMapper;

    @Autowired
    private ComicTaskMapper comicTaskMapper;

    @GetMapping("/stats")
    public Result<Map<String, Object>> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("projectCount", projectMapper.selectCount(null));
        stats.put("scriptCount", scriptMapper.selectCount(null));
        stats.put("comicCount", comicMapper.selectCount(null));
        stats.put("runningTaskCount", comicTaskMapper.countRunningTasks());
        return Result.success(stats);
    }
}
