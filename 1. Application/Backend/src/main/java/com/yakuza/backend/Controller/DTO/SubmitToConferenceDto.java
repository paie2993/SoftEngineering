package com.yakuza.backend.Controller.DTO;

import java.io.Serializable;

public class SubmitToConferenceDto implements Serializable {
    private Integer conferenceId;

    public Integer getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }
}
