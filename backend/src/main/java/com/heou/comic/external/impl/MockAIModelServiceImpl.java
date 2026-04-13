package com.heou.comic.external.impl;

import com.heou.comic.external.AIModelService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MockAIModelServiceImpl implements AIModelService {

    @Override
    public String generateScript(String topic, String style) {
        return String.format("""
            【剧本：%s】
            
            第一章：开端
            
            在一个%s的世界里，故事悄然展开。主角站在命运的十字路口，即将踏上一段不可思议的旅程...
            
            场景1：开场
            - 画面描述：晨曦初露，城市渐渐苏醒
            - 角色动作：主角缓缓睁开双眼，望向窗外
            - 对白："新的一天，新的开始..."
            
            场景2：相遇
            - 画面描述：熙熙攘攘的街道，人来人往
            - 角色动作：主角与神秘人物擦肩而过
            - 对白："那个人...好像在哪里见过？"
            
            场景3：转折
            - 画面描述：天空突然变色，异象出现
            - 角色动作：主角惊讶地抬头
            - 对白："这是...什么？"
            
            风格：%s
            """, topic, style != null ? style : "现代都市", style != null ? style : "悬疑");
    }

    @Override
    public String generateImage(String prompt, String style, String size) {
        return "https://picsum.photos/seed/" + UUID.randomUUID().toString().substring(0, 8) + "/1024/1024";
    }

    @Override
    public List<String> generateStoryBoard(String script, List<String> actors, List<String> scenes) {
        List<String> images = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            images.add("https://picsum.photos/seed/" + UUID.randomUUID().toString().substring(0, 8) + "/1024/1024");
        }
        return images;
    }

    @Override
    public Map<String, Object> analyzeTopic(String topicContent) {
        Map<String, Object> result = new HashMap<>();
        result.put("genre", "悬疑");
        result.put("mood", "紧张");
        result.put("suggestedActors", Arrays.asList("主角", "神秘人", "配角"));
        result.put("suggestedScenes", Arrays.asList("城市街道", "神秘建筑", "夜晚公园"));
        result.put("keywords", Arrays.asList("神秘", "冒险", "探索"));
        return result;
    }
}
