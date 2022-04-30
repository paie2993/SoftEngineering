package com.yakuza.backend.Controller.DTO;

import com.yakuza.backend.Model.UserModel.Author;

import java.io.Serializable;

public class AuthorDto implements Serializable {
    private Integer id;
    private String username;

    public void setId(Integer id) {
        this.id = id;
    }

    public AuthorDto(Author author) {
        this.id = author.getId();
        this.username = author.getUsername();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
