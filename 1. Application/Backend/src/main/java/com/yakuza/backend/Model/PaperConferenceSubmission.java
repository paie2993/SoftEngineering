package com.yakuza.backend.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "dbo", name = "Paper_Conference")
public class PaperConferenceSubmission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "Paper_id", nullable = false)
    private Paper paper;

    @ManyToOne
    @JoinColumn(name = "Conference_id", nullable = false)
    private Conference conference;

    private String status;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public Paper getPaper() {
        return paper;
    }

    public Conference getConference() {
        return conference;
    }

    public String getStatus() {
        return status;
    }
}
