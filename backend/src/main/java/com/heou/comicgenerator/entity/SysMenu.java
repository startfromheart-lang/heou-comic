package com.heou.comicgenerator.entity;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SysMenu {
    private Integer id;
    private String menuName;
    private Integer parentId;
    private Integer menuType;
    private String path;
    private String component;
    private String permission;
    private String icon;
    private Integer sort;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<SysMenu> children;
}