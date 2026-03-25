package com.heou.comic.entity;

import com.heou.comic.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Scene extends BaseEntity {
    private String sceneName;
    private String positioning;
    private String imageUrl;
    private String description;
    private Integer status;
    private Long createUserId;
}
