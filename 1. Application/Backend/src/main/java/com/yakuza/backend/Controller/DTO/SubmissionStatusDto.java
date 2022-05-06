package com.yakuza.backend.Controller.DTO;

import com.yakuza.backend.Model.PaperConferenceSubmission;

public class SubmissionStatusDto {
    private Integer conferenceId;
    private String conferenceName;
    private String status;

    public SubmissionStatusDto(PaperConferenceSubmission paperConferenceSubmission) {
        this.conferenceId = paperConferenceSubmission.getConference().getId();
        this.conferenceName = paperConferenceSubmission.getConference().getName();
        this.status = paperConferenceSubmission.getStatus();
    }

    public Integer getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
