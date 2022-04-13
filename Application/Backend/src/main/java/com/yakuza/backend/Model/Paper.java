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
    @NotNull
    private String status;
    private String cameraCopyUrl;
    private String fullCopyUrl;
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
    @ManyToMany(mappedBy = "papers")
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
}
