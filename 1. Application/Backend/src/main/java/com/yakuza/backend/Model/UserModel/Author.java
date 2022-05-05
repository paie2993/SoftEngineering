package com.yakuza.backend.Model.UserModel;

import com.yakuza.backend.Model.Paper;
import com.yakuza.backend.Model.TopicOfInterest;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(schema = "dbo", name = "Authors")
public class Author extends CMSUser{
    @ManyToMany(mappedBy = "authors")

    private Set<Paper> papers;

    public Set<Paper> getPapers() {
        return papers;
    }
}
