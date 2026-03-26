package com.heou.comic.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseEntity implements Serializable {
    private Long id;
    private String createTime;
    private String updateTime;
    private Integer deleted;
}
