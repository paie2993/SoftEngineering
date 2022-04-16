package com.yakuza.backend.Controller.DTO;

import com.yakuza.backend.Model.TopicOfInterest;

import java.io.Serializable;

public class TopicOfInterestDto implements Serializable {
    private Integer id;
    private String description;

    public TopicOfInterestDto(TopicOfInterest topicOfInterest) {
        this.id = topicOfInterest.getId();
        this.description = topicOfInterest.getDescription();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public TopicOfInterestDto(Integer id, String description) {
        this.id = id;
        this.description = description;
    }
}
