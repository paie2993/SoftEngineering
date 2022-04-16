package com.yakuza.backend.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(schema = "dbo", name = "Keywords")
public class Keyword implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String name;
    @ManyToMany(mappedBy = "keywords")
    private Set<Paper> papers;
}
