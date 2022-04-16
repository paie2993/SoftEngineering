package com.yakuza.backend.Controller.DTO;

import java.io.Serializable;

public class LoginResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String token;
    private final String name;
    private final String role;
    private final Integer id;

    public String getName() {
        return name;
    }

    public LoginResponseDto(String token, String name, String role, Integer id) {
        this.token = token;
        this.name = name;
        this.role = role;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }

    public Integer getId() {
        return id;
    }
}
