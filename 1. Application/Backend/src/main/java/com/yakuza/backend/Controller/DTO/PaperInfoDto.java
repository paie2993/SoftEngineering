package com.yakuza.backend.Controller.DTO;

import com.yakuza.backend.Model.Paper;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class PaperInfoDto implements Serializable {
    Integer id;
    private String title;
    private String paperAbstract;
    private Set<String> topics;
    private Set<String> keywords;
    private Set<AuthorDto> authors;
    private Set<SubmissionStatusDto> submissions;

    private Set<CommentDto> comments;

    public PaperInfoDto(Paper paper) {
        this.id = paper.getId();
        this.title = paper.getTitle();
        this.paperAbstract = paper.getPaperAbstract();

        this.topics = new HashSet<>();
        this.keywords = new HashSet<>();
        this.authors = new HashSet<>();
        this.submissions = new HashSet<>();
        this.comments = new HashSet<>();

        for(var topic: paper.getTopicsOfInterest()) {
            this.topics.add(topic.getDescription());
        }
        for(var keyword: paper.getKeywords()) {
            this.keywords.add(keyword.getName());
        }
        for(var author: paper.getAuthors()) {
            this.authors.add(new AuthorDto(author));
        }
        for(var sub: paper.getSubmissions()) {
            this.submissions.add(new SubmissionStatusDto(sub));
        }
        for(var comment: paper.getPaperComments()) {
            var cdto = new CommentDto();
            cdto.setComment(comment.getContent());
            cdto.setReviewer_name(comment.getReviewer().getUsername());
            this.comments.add(cdto);
        }
    }

    public Set<SubmissionStatusDto> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(Set<SubmissionStatusDto> submissions) {
        this.submissions = submissions;
    }

    public Set<CommentDto> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDto> comments) {
        this.comments = comments;
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
}
