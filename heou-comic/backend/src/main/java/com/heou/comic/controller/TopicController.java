package com.heou.comic.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heou.comic.common.PageResult;
import com.heou.comic.common.Result;
import com.heou.comic.entity.Topic;
import com.heou.comic.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/list")
    public Result<PageResult<Topic>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String type) {
        Page<Topic> topicPage = topicService.getTopicPage(page, size, type);
        return Result.success(new PageResult<>(topicPage.getTotal(), topicPage.getRecords()));
    }

    @PostMapping
    public Result<Void> create(@RequestBody Topic topic) {
        topicService.createTopic(topic);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody Topic topic) {
        topicService.updateTopic(id, topic);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        topicService.removeById(id);
        return Result.success();
    }

    @PostMapping("/{id}/generate-script")
    public Result<Void> generateScript(@PathVariable Long id) {
        // 调用AI生成剧本
        return Result.success();
    }
}
