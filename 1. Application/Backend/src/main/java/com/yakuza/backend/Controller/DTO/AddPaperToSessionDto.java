package com.yakuza.backend.Controller.DTO;

import java.io.Serializable;

public class AddPaperToSessionDto implements Serializable {
    private Integer paperId;

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getPaperId() {
        return paperId;
    }
}
