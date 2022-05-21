package com.yakuza.backend.Repository;

import com.yakuza.backend.Model.PaperComment;
import com.yakuza.backend.Model.ReviewerEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperCommentRepository extends JpaRepository<PaperComment, Integer> {
}
