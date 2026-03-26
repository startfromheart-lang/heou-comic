package com.heou.comicgenerator.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ComicProject {
    private Integer id;
    private String projectName;
    private String description;
    private Integer scriptId;
    private Integer status;
    private Integer creatorId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}