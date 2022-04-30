package com.yakuza.backend.Repository;

import com.yakuza.backend.Model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.Key;
import java.util.Optional;

public interface KeywordRepository extends JpaRepository<Keyword, Integer> {
    @Override
    Optional<Keyword> findById(Integer integer);

    Optional<Keyword> findByName(String name);
}
