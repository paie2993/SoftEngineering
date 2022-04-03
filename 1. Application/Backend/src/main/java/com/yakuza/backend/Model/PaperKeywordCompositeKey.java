package com.yakuza.backend.Model;

import java.io.Serializable;

public class PaperKeywordCompositeKey implements Serializable {

    private final Paper paper;
    private final Keyword keyword;

    public PaperKeywordCompositeKey() {
        this.paper = null;
        this.keyword = null;
    }

    public PaperKeywordCompositeKey(Paper paper, Keyword keyword) {
        this.paper = paper;
        this.keyword = keyword;
    }

    public Paper getPaper() {
        return paper;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public boolean equals(Object other) {
        if (!(other instanceof PaperKeywordCompositeKey keyInst))
            return false;
        return keyInst.paper.equals(this.paper) && keyInst.keyword.equals(this.keyword);
    }

    public int hashCode() {
        return 0;
    }

}
