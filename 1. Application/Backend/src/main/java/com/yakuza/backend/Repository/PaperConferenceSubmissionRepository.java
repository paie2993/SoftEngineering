package com.yakuza.backend.Repository;

import com.yakuza.backend.Model.PaperConferenceSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PaperConferenceSubmissionRepository extends JpaRepository<PaperConferenceSubmission, Integer> {
    public Optional<PaperConferenceSubmission> findPaperConferenceSubmissionByConferenceIdAndPaperId(Integer conferenceId, Integer paperId);
}
