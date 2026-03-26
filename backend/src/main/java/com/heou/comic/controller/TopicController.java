package com.heou.comic.controller;

import com.heou.comic.common.PageResult;
import com.heou.comic.common.Result;
import com.heou.comic.entity.Topic;
import com.heou.comic.service.TopicService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/topic")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/list")
    public Result<PageResult<Topic>> list(
            @RequestParam(required = false) String topicTitle,
            @RequestParam(required = false) Integer topicType,
            @RequestParam(required = false) Integer status,
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size) {
        List<Topic> list = topicService.list(topicTitle, topicType, status);
        int start = (int) ((current - 1) * size);
        int end = Math.min(start + size.intValue(), list.size());
        List<Topic> pageList = list.subList(start, end);
        PageResult<Topic> pageResult = PageResult.of(pageList, (long) list.size(), size, current);
        return Result.success(pageResult);
    }

    @GetMapping("/hot")
    public Result<Map<String, List<Topic>>> getHotTopics() {
        Map<String, List<Topic>> result = new HashMap<>();
        result.put("daily", topicService.getDailyHot());
        result.put("weekly", topicService.getWeeklyHot());
        return Result.success(result);
    }

    @GetMapping("/{id}")
    public Result<Topic> getById(@PathVariable Long id) {
        return Result.success(topicService.getById(id));
    }

    @PostMapping
    public Result<Void> save(@RequestBody Topic topic) {
        topicService.save(topic);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(@RequestBody Topic topic) {
        topicService.update(topic);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        topicService.deleteById(id);
        return Result.success();
    }
}
