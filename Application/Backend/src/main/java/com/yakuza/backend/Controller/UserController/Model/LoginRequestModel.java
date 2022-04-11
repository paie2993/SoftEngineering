package com.yakuza.backend.Controller.UserController.Model;

import java.io.Serializable;

public class LoginRequestModel implements Serializable {
    private String username;
    private String password;

    public LoginRequestModel() {}

    public LoginRequestModel(String username, String password) {
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
