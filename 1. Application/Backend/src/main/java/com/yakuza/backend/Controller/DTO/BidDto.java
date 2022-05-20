package com.yakuza.backend.Controller.DTO;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class BidDto implements Serializable {
    @NotNull
    private int bidValue;

    public int getBidValue() {
        return bidValue;
    }

    public void setBidValue(int bidValue) {
        this.bidValue = bidValue;
    }

    public BidDto() {
    }

    public BidDto(int bidValue) {
        this.bidValue = bidValue;
    }
}
