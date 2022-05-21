package com.yakuza.backend.Controller.DTO;

public class CommentDto {
    private String reviewer_name;
    private String comment;

    public CommentDto() {
    }

    public String getReviewer_name() {
        return reviewer_name;
    }

    public void setReviewer_name(String reviewer_name) {
        this.reviewer_name = reviewer_name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
