package com.yakuza.backend.Controller.DTO;

import com.yakuza.backend.Model.ConferenceSession;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class SessionDTO implements Serializable {
    private Integer conferenceId;
    private Integer sessionId;
    private Set<PaperInfoDto> papers;

    public SessionDTO(ConferenceSession session) {
        this.conferenceId = session.getConference().getId();
        this.sessionId = session.getId();

        this.papers = new HashSet<>();
        for(var paper: session.getPapers()) {
            papers.add(new PaperInfoDto(paper));
        }
    }

    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }

    public void setPapers(Set<PaperInfoDto> papers) {
        this.papers = papers;
    }

    public Integer getConferenceId() {
        return conferenceId;
    }

    public Set<PaperInfoDto> getPapers() {
        return papers;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }
}
