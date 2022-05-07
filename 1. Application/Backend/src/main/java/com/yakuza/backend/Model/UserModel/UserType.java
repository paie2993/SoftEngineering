package com.yakuza.backend.Model.UserModel;

import com.yakuza.backend.Model.UserModel.CMSUser;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(schema = "dbo", name = "UserTypes")
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;

    @OneToMany(mappedBy = "userType")
    private Set<CMSUser> users;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}