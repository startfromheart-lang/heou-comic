package com.heou.comic.entity;

import com.heou.comic.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Topic extends BaseEntity {
    private String topicTitle;
    private String topicContent;
    private Integer topicType;
    private Integer heatValue;
    private String source;
    private Integer status;
}
