package com.yakuza.backend.Controller.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yakuza.backend.Model.Conference;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public class ConferenceInfoResponseDto implements Serializable {
    private Integer id;
    private String name;
    private String URL;
    private String organizerName;
    private String organizerEmail;
    private String organizerPhoneNumber;
    private Integer chairId;
    private String chairName;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date paperSubmissionDeadline;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date paperReviewDeadline;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date acceptanceNotificationDeadline;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date uploadingPaperDeadline;
    private Set<TopicOfInterestDto> topics;

    public ConferenceInfoResponseDto(Conference conference) {
        this.id = conference.getId();
        this.name = conference.getName();
        this.URL = conference.getURL();
        this.organizerEmail = conference.getOrganizerEmail();
        this.organizerName = conference.getOrganizerName();
        this.organizerPhoneNumber = conference.getOrganizerPhoneNumber();
        this.chairId = conference.getChair().getId();
        this.chairName = conference.getChair().getUsername();
        this.paperReviewDeadline = conference.getPaperReviewDeadline();
        this.paperSubmissionDeadline = conference.getPaperSubmissionDeadline();
        this.acceptanceNotificationDeadline = conference.getAcceptanceNotificationDeadline();
        this.uploadingPaperDeadline = conference.getUploadingPaperDeadline();
        this.topics = conference.getTopicsOfInterest()
                .stream()
                .map(TopicOfInterestDto::new)
                .collect(Collectors.toSet());
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setChairId(Integer chairId) {
        this.chairId = chairId;
    }

    public void setChairName(String chairName) {
        this.chairName = chairName;
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

    public void setTopics(Set<TopicOfInterestDto> topics) {
        this.topics = topics;
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

    public String getOrganizerName() {
        return organizerName;
    }

    public String getOrganizerEmail() {
        return organizerEmail;
    }

    public String getOrganizerPhoneNumber() {
        return organizerPhoneNumber;
    }

    public Integer getChairId() {
        return chairId;
    }

    public String getChairName() {
        return chairName;
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

    public Set<TopicOfInterestDto> getTopics() {
        return topics;
    }
}

