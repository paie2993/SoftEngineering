package com.yakuza.backend.Repository;

import com.yakuza.backend.Model.UserModel.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByUsername(String username);

    Author getByUsername(String name);
}
