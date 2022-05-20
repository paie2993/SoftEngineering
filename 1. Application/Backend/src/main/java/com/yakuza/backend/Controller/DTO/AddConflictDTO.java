package com.yakuza.backend.Controller.DTO;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AddConflictDTO implements Serializable {
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AddConflictDTO() {
    }

    public AddConflictDTO(String description) {
        this.description = description;
    }

    @NotNull
    private String description;
}
