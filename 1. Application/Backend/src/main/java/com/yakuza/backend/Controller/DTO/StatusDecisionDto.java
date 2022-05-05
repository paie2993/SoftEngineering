package com.yakuza.backend.Controller.DTO;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class StatusDecisionDto implements Serializable {
    @NotNull
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
