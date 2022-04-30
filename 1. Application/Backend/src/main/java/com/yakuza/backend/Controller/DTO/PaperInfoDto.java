package com.yakuza.backend.Controller.DTO;

import com.yakuza.backend.Model.Paper;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class PaperInfoDto implements Serializable {
    private String title;
    private String paperAbstract;
    private Set<String> topics;
    private Set<String> keywords;
    private Set<AuthorDto> authors;
    private Integer conferenceId;
    private String status;

    public PaperInfoDto(Paper paper) {
        this.title = paper.getTitle();
        this.paperAbstract = paper.getPaperAbstract();
        this.conferenceId = paper.getConference().getId();
        this.status = paper.getStatus();

        this.topics = new HashSet<>();
        this.keywords = new HashSet<>();
        this.authors = new HashSet<>();

        for(var topic: paper.getTopicsOfInterest()) {
            this.topics.add(topic.getDescription());
        }
        for(var keyword: paper.getKeywords()) {
            this.keywords.add(keyword.getName());
        }
        for(var author: paper.getAuthors()) {
            this.authors.add(new AuthorDto(author));
        }
    }

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

    public void setAuthors(Set<AuthorDto> authors) {
        this.authors = authors;
    }

    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Set<AuthorDto> getAuthors() {
        return authors;
    }

    public Integer getConferenceId() {
        return conferenceId;
    }

    public String getStatus() {
        return status;
    }
}
