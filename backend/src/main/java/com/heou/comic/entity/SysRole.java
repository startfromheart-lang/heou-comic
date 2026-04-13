package com.heou.comic.entity;

import com.heou.comic.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysRole extends BaseEntity {
    private String roleName;
    private String roleCode;
    private String description;
    private Integer status;
}
