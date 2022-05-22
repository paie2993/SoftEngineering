package com.yakuza.backend.Repository;

import com.yakuza.backend.Model.ReviewerEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaperEvaluationRepository extends JpaRepository<ReviewerEvaluation, Integer> {
    Optional<ReviewerEvaluation> findByReviewerIdAndPaperId(Integer reviewer_id, Integer paper_id);
    boolean existsByReviewerIdAndPaperId(Integer reviewer_id, Integer paper_id);
}
