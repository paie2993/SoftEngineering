package com.yakuza.backend.Repository;

import com.yakuza.backend.Model.UserModel.Reviewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewerRepository extends JpaRepository<Reviewer, Integer> {
}
