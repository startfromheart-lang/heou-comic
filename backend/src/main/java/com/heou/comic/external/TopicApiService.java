package com.heou.comic.external;

import java.util.List;
import java.util.Map;

public interface TopicApiService {
    List<Map<String, Object>> getDailyHotTopics();
    
    List<Map<String, Object>> getWeeklyHotTopics();
}
