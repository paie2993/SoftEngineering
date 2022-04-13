package com.yakuza.backend.Model;

import com.yakuza.backend.Model.UserModel.Chair;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(schema = "dbo", name = "Conferences")
public class Conference implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String name;
    private String URL;
    @ManyToOne
    @JoinColumn(name = "Chair_id", nullable = false)
    private Chair chair;
    @ManyToOne
    @JoinColumn(name = "Organizer_id")
    private Organizer organizer;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date paperSubmissionDeadline;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date paperReviewDeadline;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date acceptanceNotificationDeadline;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date uploadingPaperDeadline;
    @OneToMany(mappedBy = "conference")
    private Set<ConferenceSession> sessions;
    @ManyToMany
    @JoinTable(
            name = "Conferences_Topics",
            joinColumns = @JoinColumn(name = "conference_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private Set<TopicOfInterest> topicsOfInterest;
}
