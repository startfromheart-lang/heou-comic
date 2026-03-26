package com.heou.comic.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("comic_comic")
public class Comic {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private String title;
    
    private Long projectId;
    
    private String content;
    
    private String htmlUrl;
    
    private Integer chapterCount;
    
    private Integer status;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}
