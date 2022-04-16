package com.yakuza.backend.Controller.DTO;

import java.io.Serializable;

public class ConferenceListItemDto implements Serializable {
    private Integer id;
    private String name;

    public ConferenceListItemDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
