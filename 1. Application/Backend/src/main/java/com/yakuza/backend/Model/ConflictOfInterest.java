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
}
