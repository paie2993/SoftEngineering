package com.yakuza.backend.Model;

import javax.persistence.*;

//@Entity
//@Table(schema = "dbo", name = "Keywords")
public class Keyword {

//    @Id
//    @SequenceGenerator(
//            name = "keywords_sequence",
//            sequenceName = "keywords_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "keywords_sequence"
//    )
    private final Long id;
//    @Column(name = "description")
    private String description;

    public Keyword(Long id, String description) {
        this.id = id;
        this.description = description;
    }


    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
