package com.yakuza.backend.Repository;

import com.yakuza.backend.Model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperRepository extends JpaRepository<Paper, Integer> {
}
