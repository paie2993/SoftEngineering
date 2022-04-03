package com.yakuza.backend.Repository;

import com.yakuza.backend.Model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
}
