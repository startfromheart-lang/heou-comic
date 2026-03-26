package com.heou.comicgenerator.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ComicCharacter {
    private Integer id;
    private String characterName;
    private String positioning;
    private String personality;
    private String imageUrl;
    private Integer status;
    private Integer creatorId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}