package com.yakuza.backend.Repository;

import com.yakuza.backend.Model.UserModel.CMSUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CMSUser, Integer> {
    CMSUser findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    CMSUser getCMSUserByUsername(String username);
}
