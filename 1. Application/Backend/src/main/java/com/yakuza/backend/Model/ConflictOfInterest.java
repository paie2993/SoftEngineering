package com.yakuza.backend.Model;

import com.yakuza.backend.Model.UserModel.Reviewer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(schema = "dbo", name = "ConflictsOfInterest")
public class ConflictOfInterest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "Paper_id", nullable = false)
    private Paper paper;
    private String description;
    @ManyToOne
    @JoinColumn(name = "Reviewer_id", nullable = false)
    private Reviewer reviewer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Reviewer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Reviewer reviewer) {
        this.reviewer = reviewer;
    }
}


