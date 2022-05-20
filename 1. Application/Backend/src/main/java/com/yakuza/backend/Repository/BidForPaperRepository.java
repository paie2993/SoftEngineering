package com.yakuza.backend.Repository;

import com.yakuza.backend.Model.BidForPaper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BidForPaperRepository extends JpaRepository<BidForPaper, Integer> {
    Optional<BidForPaper> findTopByPaperIdOrderByInterestDesc(Integer paper_id);
    void deleteAllByReviewerIdAndPaperId(Integer reviewer_id, Integer paper_id);
}
