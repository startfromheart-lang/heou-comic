package com.heou.comicgenerator.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ComicScene {
    private Integer id;
    private Integer status;
    private Integer creatorId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String sceneName;
    private String positioning;
    private String imageUrl;
}
