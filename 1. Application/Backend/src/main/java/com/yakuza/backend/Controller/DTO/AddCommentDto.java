package com.yakuza.backend.Controller.DTO;

import java.io.Serializable;

public class AddCommentDto implements Serializable {
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public AddCommentDto() {
    }
}
