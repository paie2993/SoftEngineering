package com.yakuza.backend.Repository;

import com.yakuza.backend.Model.UserModel.Reviewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ReviewerRepository extends JpaRepository<Reviewer, Integer> {
    public Optional<Reviewer> findByUsername(String username);
}
