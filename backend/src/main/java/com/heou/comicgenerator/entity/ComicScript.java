package com.heou.comicgenerator.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ComicScript {
    private Integer id;
    private String scriptName;
    private String content;
    private Integer status;
    private Integer creatorId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}