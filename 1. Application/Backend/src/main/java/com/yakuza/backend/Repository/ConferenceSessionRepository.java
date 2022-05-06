package com.yakuza.backend.Repository;

import com.yakuza.backend.Model.ConferenceSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConferenceSessionRepository extends JpaRepository<ConferenceSession, Integer> {
    public Optional<ConferenceSession> findConferenceSessionByIdAndConferenceId(Integer id, Integer conferenceId);
}
