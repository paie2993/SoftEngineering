package com.yakuza.backend.Model;

import com.yakuza.backend.Model.UserModel.Reviewer;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "dbo", name = "BidsForPapers")
public class BidForPaper implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "reviewer_id", nullable = false)
    private Reviewer reviewer;
    @ManyToOne
    @JoinColumn(name = "paper_id", nullable = false)
    private Paper paper;
    private Integer interest;
}

