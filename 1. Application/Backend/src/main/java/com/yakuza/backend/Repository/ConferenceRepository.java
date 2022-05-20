package com.yakuza.backend.Repository;

import com.yakuza.backend.Model.Conference;
import com.yakuza.backend.Model.Paper;
import com.yakuza.backend.Model.UserModel.Chair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Integer> {
    public Optional<Conference> findById(Integer id);
}
