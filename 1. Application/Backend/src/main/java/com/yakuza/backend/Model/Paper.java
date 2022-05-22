package com.yakuza.backend.Model;

import com.yakuza.backend.Model.UserModel.Author;
import com.yakuza.backend.Model.UserModel.Reviewer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(schema = "dbo", name = "Papers")
public class Paper implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String title;
    private String paperAbstract;
    private String cameraCopyUrl;
    private String fullCopyUrl;
    @OneToMany(mappedBy = "paper")
    Set<PaperConferenceSubmission> submissions;
    @ManyToMany
    @JoinTable(
            name = "Papers_Topics",
            joinColumns = @JoinColumn(name = "paper_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private Set<TopicOfInterest> topicsOfInterest;
    @ManyToMany
    @JoinTable(
            name = "Papers_Keywords",
            joinColumns = @JoinColumn(name = "paper_id"),
            inverseJoinColumns = @JoinColumn(name = "keyword_id")
    )
    private Set<Keyword> keywords;
    @ManyToMany
    @JoinTable(
            name = "Authors_Papers",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "paper_id")
    )
    private Set<Author> authors;
    @ManyToMany(mappedBy = "papers")
    private Set<ConferenceSession> conferenceSessions;
    @OneToMany(mappedBy = "paper")
    private Set<ConflictOfInterest> conflictsOfInterest;
    @OneToMany(mappedBy = "paper")
    private Set<BidForPaper> bidsForPaper;
    @ManyToMany(mappedBy = "assignedPapers")
    private Set<Reviewer> reviewers;
    @OneToMany(mappedBy = "paper")
    private Set<PaperComment> paperComments;
    @OneToMany(mappedBy = "paper")
    private Set<ChairEvaluation> chairEvaluations;
    @OneToMany(mappedBy = "paper")
    private Set<ReviewerEvaluation> reviewerEvaluations;
    @Lob
    private byte[] fullCopy;
    @Lob
    private byte[] cameraCopy;

    public Set<PaperConferenceSubmission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(Set<PaperConferenceSubmission> submissions) {
        this.submissions = submissions;
    }

    public byte[] getFullCopy() {
        return fullCopy;
    }

    public void setFullCopy(byte[] fullCopy) {
        this.fullCopy = fullCopy;
    }



    public Set<TopicOfInterest> getTopicsOfInterest() {
        return topicsOfInterest;
    }

    public Set<Keyword> getKeywords() {
        return keywords;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public Set<ConferenceSession> getConferenceSessions() {
        return conferenceSessions;
    }

    public Set<ConflictOfInterest> getConflictsOfInterest() {
        return conflictsOfInterest;
    }

    public Set<BidForPaper> getBidsForPaper() {
        return bidsForPaper;
    }

    public Set<Reviewer> getReviewers() {
        return reviewers;
    }

    public Set<PaperComment> getPaperComments() {
        return paperComments;
    }

    public Set<ChairEvaluation> getChairEvaluations() {
        return chairEvaluations;
    }

    public Set<ReviewerEvaluation> getReviewerEvaluations() {
        return reviewerEvaluations;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPaperAbstract(String paperAbstract) {
        this.paperAbstract = paperAbstract;
    }

    public void setCameraCopyUrl(String cameraCopyUrl) {
        this.cameraCopyUrl = cameraCopyUrl;
    }

    public void setFullCopyUrl(String fullCopyUrl) {
        this.fullCopyUrl = fullCopyUrl;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPaperAbstract() {
        return paperAbstract;
    }

    public String getCameraCopyUrl() {
        return cameraCopyUrl;
    }

    public String getFullCopyUrl() {
        return fullCopyUrl;
    }

    public void setTopicsOfInterest(Set<TopicOfInterest> topicsOfInterest) {
        this.topicsOfInterest = topicsOfInterest;
    }

    public void setKeywords(Set<Keyword> keywords) {
        this.keywords = keywords;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public void setConferenceSessions(Set<ConferenceSession> conferenceSessions) {
        this.conferenceSessions = conferenceSessions;
    }

    public void setConflictsOfInterest(Set<ConflictOfInterest> conflictsOfInterest) {
        this.conflictsOfInterest = conflictsOfInterest;
    }

    public void setBidsForPaper(Set<BidForPaper> bidsForPaper) {
        this.bidsForPaper = bidsForPaper;
    }

    public void setReviewers(Set<Reviewer> reviewers) {
        this.reviewers = reviewers;
    }

    public void setPaperComments(Set<PaperComment> paperComments) {
        this.paperComments = paperComments;
    }

    public void setChairEvaluations(Set<ChairEvaluation> chairEvaluations) {
        this.chairEvaluations = chairEvaluations;
    }

    public void setReviewerEvaluations(Set<ReviewerEvaluation> reviewerEvaluations) {
        this.reviewerEvaluations = reviewerEvaluations;
    }

    public byte[] getCameraCopy() {
        return cameraCopy;
    }

    public void setCameraCopy(byte[] cameraCopy) {
        this.cameraCopy = cameraCopy;
    }
}
