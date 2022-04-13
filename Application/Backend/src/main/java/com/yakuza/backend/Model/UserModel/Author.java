package com.yakuza.backend.Model.UserModel;

import com.yakuza.backend.Model.Paper;
import com.yakuza.backend.Model.TopicOfInterest;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(schema = "dbo", name = "Authors")
public class Author extends CMSUser{
    @ManyToMany
    @JoinTable(
            name = "Authors_Papers",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "paper_id")
    )
    private Set<Paper> papers;
}
