package com.heou.comic.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginUser implements Serializable {
    private Long userId;
    private String username;
    private String nickname;
    private Integer isAdmin;
    private String token;
}
