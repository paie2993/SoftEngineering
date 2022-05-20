package com.yakuza.backend.Repository;

import com.yakuza.backend.Model.ConflictOfInterest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConflictOfInterestRepository extends JpaRepository<ConflictOfInterest, Integer> {
}
