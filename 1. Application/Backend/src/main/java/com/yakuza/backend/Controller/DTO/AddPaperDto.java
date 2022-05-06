package com.yakuza.backend.Controller.DTO;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

public class AddPaperDto implements Serializable {
    @NotNull
    private String title;
    @NotNull
    private String paperAbstract;
    @NotNull
    private Set<String> topics;
    @NotNull
    private Set<String> keywords;
    @NotNull
    private Set<String> otherAuthors;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPaperAbstract(String paperAbstract) {
        this.paperAbstract = paperAbstract;
    }

    public void setTopics(Set<String> topics) {
        this.topics = topics;
    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }

    public void setOtherAuthors(Set<String> otherAuthors) {
        this.otherAuthors = otherAuthors;
    }

    public String getTitle() {
        return title;
    }

    public String getPaperAbstract() {
        return paperAbstract;
    }

    public Set<String> getTopics() {
        return topics;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public Set<String> getOtherAuthors() {
        return otherAuthors;
    }

    public AddPaperDto(String title, String paperAbstract, Set<String> topics, Set<String> keywords, Set<String> otherAuthors) {
        this.title = title;
        this.paperAbstract = paperAbstract;
        this.topics = topics;
        this.keywords = keywords;
        this.otherAuthors = otherAuthors;
    }
}
