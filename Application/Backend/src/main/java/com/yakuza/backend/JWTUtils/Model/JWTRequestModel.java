package com.yakuza.backend.JWTUtils.Model;

import java.io.Serializable;

public class JWTRequestModel implements Serializable {
    private String username;
    private String password;

    public JWTRequestModel() {}

    public JWTRequestModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
