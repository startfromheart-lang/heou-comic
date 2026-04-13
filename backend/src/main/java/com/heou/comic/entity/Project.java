package com.heou.comic.entity;

import com.heou.comic.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Project extends BaseEntity {
    private String projectName;
    private String description;
    private String coverImage;
    private Integer status;
    private Integer currentStep;
    private Integer totalSteps;
    private Integer autoMode;
    private Long createUserId;
}
