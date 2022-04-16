package com.yakuza.backend.Model;

import com.yakuza.backend.Model.UserModel.Chair;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "dbo", name = "ChairsEvaluations")
public class ChairEvaluation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String judgement;
    @ManyToOne
    @JoinColumn(name = "chair_id", nullable = false)
    private Chair chair;
    @ManyToOne
    @JoinColumn(name = "paper_id", nullable = false)
    private Paper paper;
}
