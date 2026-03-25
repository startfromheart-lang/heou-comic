package com.heou.comic.entity;

import com.heou.comic.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Script extends BaseEntity {
    private String scriptName;
    private String content;
    private Integer chapterCount;
    private String description;
    private Integer status;
    private Long createUserId;
}
