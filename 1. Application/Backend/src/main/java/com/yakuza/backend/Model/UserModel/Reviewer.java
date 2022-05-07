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

    public Set<TopicOfInterest> getTopicsOfInterest() {
        return topicsOfInterest;
    }

    public void setTopicsOfInterest(Set<TopicOfInterest> topicsOfInterest) {
        this.topicsOfInterest = topicsOfInterest;
    }

    public Set<ConflictOfInterest> getConflictsOfInterest() {
        return conflictsOfInterest;
    }

    public void setConflictsOfInterest(Set<ConflictOfInterest> conflictsOfInterest) {
        this.conflictsOfInterest = conflictsOfInterest;
    }

    public Set<BidForPaper> getBidsForPaper() {
        return bidsForPaper;
    }

    public void setBidsForPaper(Set<BidForPaper> bidsForPaper) {
        this.bidsForPaper = bidsForPaper;
    }

    public Set<Paper> getAssignedPapers() {
        return assignedPapers;
    }

    public void setAssignedPapers(Set<Paper> assignedPapers) {
        this.assignedPapers = assignedPapers;
    }

    public Set<PaperComment> getPaperComments() {
        return paperComments;
    }

    public void setPaperComments(Set<PaperComment> paperComments) {
        this.paperComments = paperComments;
    }

    public Set<ReviewerEvaluation> getReviewerEvaluations() {
        return reviewerEvaluations;
    }

    public void setReviewerEvaluations(Set<ReviewerEvaluation> reviewerEvaluations) {
        this.reviewerEvaluations = reviewerEvaluations;
    }
}
