package com.yakuza.backend.Model.UserModel;

import com.yakuza.backend.Model.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(schema = "dbo", name = "Reviewers")
public class Reviewer extends CMSUser{
    @ManyToMany
    @JoinTable(
            name = "Reviewers_Topics",
            joinColumns = @JoinColumn(name = "reviewer_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private Set<TopicOfInterest> topicsOfInterest;
    @OneToMany(mappedBy = "reviewer")
    private Set<ConflictOfInterest> conflictsOfInterest;
    @OneToMany(mappedBy = "reviewer")
    private Set<BidForPaper> bidsForPaper;
    @ManyToMany
    @JoinTable(
            name = "AssignedPapers",
            joinColumns = @JoinColumn(name = "reviewer_id"),
            inverseJoinColumns = @JoinColumn(name = "paper_id")
    )
    private Set<Paper> assignedPapers;
    @OneToMany(mappedBy = "reviewer")
    private Set<PaperComment> paperComments;
    @OneToMany(mappedBy = "reviewer")
    private Set<ReviewerEvaluation> reviewerEvaluations;
}
