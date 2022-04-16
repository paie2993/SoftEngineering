package com.yakuza.backend.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(schema = "dbo", name = "ConferenceSessions")
public class ConferenceSession implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "Conference_id", nullable = false)
    private Conference conference;
    @ManyToMany
    @JoinTable(
            name = "ConferenceSessions_Papers",
            joinColumns = @JoinColumn(name = "conferencesession_id"),
            inverseJoinColumns = @JoinColumn(name = "paper_id")
    )
    private Set<Paper> papers;
}
