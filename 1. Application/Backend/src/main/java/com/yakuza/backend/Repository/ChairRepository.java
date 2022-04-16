package com.yakuza.backend.Repository;

import com.yakuza.backend.Model.UserModel.Chair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChairRepository extends JpaRepository<Chair, Integer> {
}
