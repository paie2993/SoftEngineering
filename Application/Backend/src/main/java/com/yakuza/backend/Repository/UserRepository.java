package com.yakuza.backend.Repository;

import com.yakuza.backend.Model.CMSUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<CMSUser, Integer> {
    CMSUser findByUsername(String username);
    Boolean existsByUsername(String username);
}
