package com.yakuza.backend.Repository;

import com.yakuza.backend.Model.ConferenceSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceSessionRepository extends JpaRepository<ConferenceSession, Integer> {
}
