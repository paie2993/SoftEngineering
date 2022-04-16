package com.yakuza.backend.Model;

import com.yakuza.backend.Model.UserModel.Reviewer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(schema = "dbo", name = "TopicsOfInterest")
public class TopicOfInterest implements Serializable {
    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Set<Conference> getConferences() {
        return conferences;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setConferences(Set<Conference> conferences) {
        this.conferences = conferences;
    }

    public Set<Reviewer> getReviewers() {
        return reviewers;
    }

    public Set<Paper> getPapers() {
        return papers;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @ManyToMany(mappedBy = "topicsOfInterest")
    private Set<Conference> conferences;
    @ManyToMany(mappedBy = "topicsOfInterest")
    private Set<Reviewer> reviewers;
    @ManyToMany(mappedBy = "topicsOfInterest")
    private Set<Paper> papers;
}
