package com.yakuza.backend.Model;

import com.yakuza.backend.Model.UserModel.Reviewer;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "dbo", name = "ReviewersEvaluations")
public class ReviewerEvaluation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String judgement;
    @ManyToOne
    @JoinColumn(name = "reviewer_id", nullable = false)
    private Reviewer reviewer;
    @ManyToOne
    @JoinColumn(name = "paper_id", nullable = false)
    private Paper paper;
}
