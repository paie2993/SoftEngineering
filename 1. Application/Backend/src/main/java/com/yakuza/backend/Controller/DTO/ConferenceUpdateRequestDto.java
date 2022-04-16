package com.yakuza.backend.Controller.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class ConferenceUpdateRequestDto implements Serializable {
    private String name;
    private String URL;
    private String organizerName;
    private String organizerEmail;
    private String organizerPhoneNumber;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date paperSubmissionDeadline;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date paperReviewDeadline;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date acceptanceNotificationDeadline;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date uploadingPaperDeadline;
    private Set<String> topics;

    public String getName() {
        return name;
    }

    public String getURL() {
        return URL;
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

    public Set<String> getTopics() {
        return topics;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setURL(String URL) {
        this.URL = URL;
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

    public void setTopics(Set<String> topics) {
        this.topics = topics;
    }
}
