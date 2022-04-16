package com.yakuza.backend.Repository;

import com.yakuza.backend.Model.TopicOfInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<TopicOfInterest, Integer> {
    Optional<TopicOfInterest> findByDescription(String desc);
}
