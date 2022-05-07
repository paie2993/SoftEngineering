package com.yakuza.backend.Controller.DTO;

import java.io.Serializable;
import java.util.Set;

public class ReviewerTopicOfInterestDto implements Serializable {
    private Set<String> topics;

    public Set<String> getTopics() {
        return topics;
    }

    public void setTopics(Set<String> topics) {
        this.topics = topics;
    }
}
