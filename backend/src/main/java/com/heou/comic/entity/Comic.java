package com.heou.comic.entity;

import com.heou.comic.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Comic extends BaseEntity {
    private String comicName;
    private Long projectId;
    private String description;
    private String coverImage;
    private Integer chapterCount;
    private Integer status;
    private Long createUserId;
}
