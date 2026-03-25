package com.heou.comic.external;

import java.util.List;
import java.util.Map;

public interface AIModelService {
    String generateScript(String topic, String style);
    
    String generateImage(String prompt, String style, String size);
    
    List<String> generateStoryBoard(String script, List<String> actors, List<String> scenes);
    
    Map<String, Object> analyzeTopic(String topicContent);
}
