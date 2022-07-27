package com.HowlingWolfe.HowlingWolfe.repositories;

import com.HowlingWolfe.HowlingWolfe.models.SameDayBool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SameDayRepo extends JpaRepository<SameDayBool, Integer> {

    SameDayBool findByType(String type);
}
