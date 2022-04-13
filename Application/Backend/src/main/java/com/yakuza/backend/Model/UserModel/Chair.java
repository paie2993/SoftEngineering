package com.yakuza.backend.Model.UserModel;

import com.yakuza.backend.Model.ChairEvaluation;
import com.yakuza.backend.Model.Conference;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(schema = "dbo", name = "Chairs")
public class Chair extends CMSUser{
    @OneToMany(mappedBy = "chair")
    private Set<Conference> conferences;
    @OneToMany(mappedBy = "chair")
    private Set<ChairEvaluation> chairEvaluations;
}
