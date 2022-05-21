package com.yakuza.backend.Controller.DTO;

import java.io.Serializable;

public class PaperEvaluationDto implements Serializable {
    private String evaluationDecision;
    private PaperInfoDto paper;

    public PaperEvaluationDto(String evaluationDecision, PaperInfoDto paper) {
        this.evaluationDecision = evaluationDecision;
        this.paper = paper;
    }

    public String getEvaluationDecision() {
        return evaluationDecision;
    }

    public void setEvaluationDecision(String evaluationDecision) {
        this.evaluationDecision = evaluationDecision;
    }

    public PaperInfoDto getPaper() {
        return paper;
    }

    public void setPaper(PaperInfoDto paper) {
        this.paper = paper;
    }
}
