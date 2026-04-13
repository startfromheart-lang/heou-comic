package com.heou.comic.entity;

import com.heou.comic.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Task extends BaseEntity {
    private String taskName;
    private Integer taskType;
    private Long projectId;
    private Long topicId;
    private Integer status;
    private Integer progress;
    private String result;
    private String errorMsg;
    private String startTime;
    private String endTime;
    private Long createUserId;
}
