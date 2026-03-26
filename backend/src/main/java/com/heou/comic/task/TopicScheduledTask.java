package com.heou.comic.task;

import com.heou.comic.entity.Topic;
import com.heou.comic.external.TopicApiService;
import com.heou.comic.service.TopicService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TopicScheduledTask {

    private final TopicApiService topicApiService;
    private final TopicService topicService;

    public TopicScheduledTask(TopicApiService topicApiService, TopicService topicService) {
        this.topicApiService = topicApiService;
        this.topicService = topicService;
    }

    @Scheduled(cron = "0 0 6 * * ?")
    public void updateDailyTopics() {
        List<Map<String, Object>> dailyTopics = topicApiService.getDailyHotTopics();
        for (Map<String, Object> topicData : dailyTopics) {
            Topic topic = new Topic();
            topic.setTopicTitle((String) topicData.get("title"));
            topic.setTopicContent("自动获取的热门选题内容");
            topic.setTopicType(1);
            topic.setHeatValue((Integer) topicData.get("heatValue"));
            topic.setSource((String) topicData.get("source"));
            topic.setStatus(1);
            topicService.save(topic);
        }
    }

    @Scheduled(cron = "0 0 6 ? * MON")
    public void updateWeeklyTopics() {
        List<Map<String, Object>> weeklyTopics = topicApiService.getWeeklyHotTopics();
        for (Map<String, Object> topicData : weeklyTopics) {
            Topic topic = new Topic();
            topic.setTopicTitle((String) topicData.get("title"));
            topic.setTopicContent("自动获取的周热门选题内容");
            topic.setTopicType(2);
            topic.setHeatValue((Integer) topicData.get("heatValue"));
            topic.setSource((String) topicData.get("source"));
            topic.setStatus(1);
            topicService.save(topic);
        }
    }
}
