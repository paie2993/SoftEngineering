package com.yakuza.backend.Model;

import com.yakuza.backend.Model.UserModel.Reviewer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(schema = "dbo", name = "TopicsOfInterest")
public class TopicOfInterest implements Serializable {
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
