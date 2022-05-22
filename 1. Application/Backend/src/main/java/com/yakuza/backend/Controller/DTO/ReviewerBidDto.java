package com.yakuza.backend.Controller.DTO;

import java.io.Serializable;

public class ReviewerBidDto implements Serializable {
    private Integer bidValue;
    private Integer paperId;
    private Integer reviewerId;

    public Integer getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Integer reviewerId) {
        this.reviewerId = reviewerId;
    }

    public ReviewerBidDto() {
    }

    public Integer getBidValue() {
        return bidValue;
    }

    public void setBidValue(Integer bidValue) {
        this.bidValue = bidValue;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }
}
