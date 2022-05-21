package com.yakuza.backend.Controller.DTO;

import javax.validation.constraints.NotNull;

public class AddEvaluationDTO {
    @NotNull
    private String decision;

    public AddEvaluationDTO() {
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}
