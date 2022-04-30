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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPapers(Set<Paper> papers) {
        this.papers = papers;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Paper> getPapers() {
        return papers;
    }

    @NotNull
    private String name;
    @ManyToMany(mappedBy = "keywords")
    private Set<Paper> papers;
}
