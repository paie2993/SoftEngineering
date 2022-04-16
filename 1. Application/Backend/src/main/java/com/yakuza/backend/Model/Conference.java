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
    private String organizerName;
    private String organizerEmail;
    private String organizerPhoneNumber;
    @ManyToOne
    @JoinColumn(name = "Chair_id", nullable = false)
    private Chair chair;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setChair(Chair chair) {
        this.chair = chair;
    }

    public void setPaperSubmissionDeadline(Date paperSubmissionDeadline) {
        this.paperSubmissionDeadline = paperSubmissionDeadline;
    }

    public void setPaperReviewDeadline(Date paperReviewDeadline) {
        this.paperReviewDeadline = paperReviewDeadline;
    }

    public void setAcceptanceNotificationDeadline(Date acceptanceNotificationDeadline) {
        this.acceptanceNotificationDeadline = acceptanceNotificationDeadline;
    }

    public void setUploadingPaperDeadline(Date uploadingPaperDeadline) {
        this.uploadingPaperDeadline = uploadingPaperDeadline;
    }

    public void setSessions(Set<ConferenceSession> sessions) {
        this.sessions = sessions;
    }

    public void setTopicsOfInterest(Set<TopicOfInterest> topicsOfInterest) {
        this.topicsOfInterest = topicsOfInterest;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getURL() {
        return URL;
    }

    public Chair getChair() {
        return chair;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public void setOrganizerEmail(String organizerEmail) {
        this.organizerEmail = organizerEmail;
    }

    public void setOrganizerPhoneNumber(String organizerPhoneNumber) {
        this.organizerPhoneNumber = organizerPhoneNumber;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public String getOrganizerEmail() {
        return organizerEmail;
    }

    public String getOrganizerPhoneNumber() {
        return organizerPhoneNumber;
    }

    public Date getPaperSubmissionDeadline() {
        return paperSubmissionDeadline;
    }

    public Date getPaperReviewDeadline() {
        return paperReviewDeadline;
    }

    public Date getAcceptanceNotificationDeadline() {
        return acceptanceNotificationDeadline;
    }

    public Date getUploadingPaperDeadline() {
        return uploadingPaperDeadline;
    }

    public Set<ConferenceSession> getSessions() {
        return sessions;
    }

    public Set<TopicOfInterest> getTopicsOfInterest() {
        return topicsOfInterest;
    }


}
