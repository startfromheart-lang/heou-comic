package com.heou.comic.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("comic_task")
public class ComicTask {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private String name;
    
    private Long projectId;
    
    private Integer type;
    
    private Integer status;
    
    private String progress;
    
    private String result;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}
