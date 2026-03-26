package com.heou.comicgenerator.entity;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SysUser {
    private Integer id;
    private String username;
    private String password;
    private String realName;
    private String email;
    private String phone;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<Integer> roleIds;
}