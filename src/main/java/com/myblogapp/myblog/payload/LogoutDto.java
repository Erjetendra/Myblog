package com.myblogapp.myblog.payload;

import lombok.Data;

@Data
public class LogoutDto {
    private String name;
    private String username;
    private String email;
    private String password;
}

