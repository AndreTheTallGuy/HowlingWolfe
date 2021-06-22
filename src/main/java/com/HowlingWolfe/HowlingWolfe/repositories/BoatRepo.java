package com.HowlingWolfe.HowlingWolfe.repositories;

import com.HowlingWolfe.HowlingWolfe.models.Boat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BoatRepo extends JpaRepository<Boat, Integer> {


    @Modifying
    @Query("DELETE FROM Boat WHERE id = ?1")
    void deleteByUUID(String boatId);
}
