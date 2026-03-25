package com.heou.comic.entity;

import com.heou.comic.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ComicChapter extends BaseEntity {
    private Long comicId;
    private String chapterName;
    private Integer chapterOrder;
    private String content;
    private String htmlContent;
    private Integer imageCount;
    private Integer status;
}
