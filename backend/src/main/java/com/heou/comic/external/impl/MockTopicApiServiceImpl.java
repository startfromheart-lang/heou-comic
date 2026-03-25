package com.heou.comic.external.impl;

import com.heou.comic.external.TopicApiService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MockTopicApiServiceImpl implements TopicApiService {

    private static final Random random = new Random();

    @Override
    public List<Map<String, Object>> getDailyHotTopics() {
        List<Map<String, Object>> topics = new ArrayList<>();
        String[] titles = {
            "都市传说：深夜电话亭的秘密",
            "星际迷航：未知星球的探索",
            "校园爱情：转学生的到来",
            "武林风云：失传秘籍重现",
            "机甲战士：保卫地球之战",
            "异世界转生：魔法学院生活",
            "侦探日记：离奇案件调查",
            "美食之旅：环球美食探险",
            "运动少年：足球梦想启航",
            "魔法世界：精灵与人类的友谊"
        };
        
        for (int i = 0; i < 10; i++) {
            Map<String, Object> topic = new HashMap<>();
            topic.put("title", titles[i]);
            topic.put("heatValue", 8000 + random.nextInt(2000));
            topic.put("source", "热门榜单");
            topic.put("category", "漫画");
            topics.add(topic);
        }
        
        topics.sort((a, b) -> (Integer) b.get("heatValue") - (Integer) a.get("heatValue"));
        return topics;
    }

    @Override
    public List<Map<String, Object>> getWeeklyHotTopics() {
        List<Map<String, Object>> topics = new ArrayList<>();
        String[] titles = {
            "末日求生：最后的希望",
            "古风言情：宫廷爱情故事",
            "超级英雄：平凡人的觉醒",
            "灵异事件：超自然调查",
            "职场风云：新人的成长",
            "宠物日记：萌宠的日常",
            "历史传奇：英雄的故事",
            "音乐梦想：追逐梦想之旅",
            "游戏世界：虚拟冒险",
            "奇幻森林：精灵王国"
        };
        
        for (int i = 0; i < 10; i++) {
            Map<String, Object> topic = new HashMap<>();
            topic.put("title", titles[i]);
            topic.put("heatValue", 5000 + random.nextInt(3000));
            topic.put("source", "周榜");
            topic.put("category", "漫画");
            topics.add(topic);
        }
        
        topics.sort((a, b) -> (Integer) b.get("heatValue") - (Integer) a.get("heatValue"));
        return topics;
    }
}
