package com.yakuza.backend.JWTUtils.Model;

import java.io.Serializable;

public class JWTResponseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String token;
    private final String name;
    private final String role;

    public String getName() {
        return name;
    }

    public JWTResponseModel(String token, String name, String role) {
        this.token = token;
        this.name = name;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }

}
