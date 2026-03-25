package com.heou.comic.entity;

import com.heou.comic.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysConfig extends BaseEntity {
    private String configKey;
    private String configValue;
    private String configName;
    private String description;
}
