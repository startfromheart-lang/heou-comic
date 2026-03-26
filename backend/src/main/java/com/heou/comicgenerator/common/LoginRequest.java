package com.heou.comicgenerator.common;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}