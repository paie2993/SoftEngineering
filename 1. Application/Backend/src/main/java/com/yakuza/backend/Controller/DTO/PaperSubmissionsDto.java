package com.yakuza.backend.Controller.DTO;

import com.yakuza.backend.Model.Paper;
import com.yakuza.backend.Model.PaperConferenceSubmission;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class PaperSubmissionsDto implements Serializable {
    Integer id;
    private String title;
    private String paperAbstract;
    private Set<String> topics;
    private Set<String> keywords;
    private Set<AuthorDto> authors;
    private String status;

    public PaperSubmissionsDto(PaperConferenceSubmission paperConferenceSubmission) {
        var paper = paperConferenceSubmission.getPaper();

        this.status = paperConferenceSubmission.getStatus();
        this.id = paper.getId();
        this.title = paper.getTitle();
        this.paperAbstract = paper.getPaperAbstract();

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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
