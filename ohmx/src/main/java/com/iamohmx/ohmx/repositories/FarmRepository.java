package com.iamohmx.ohmx.repositories;

import com.iamohmx.ohmx.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

    @Query("SELECT f FROM Farm f JOIN FETCH f.owner JOIN FETCH f.cows WHERE f.id = :farmId")
    Optional<Farm> findByIdWithOwnerAndCows(@Param("farmId") Long farmId);
}
