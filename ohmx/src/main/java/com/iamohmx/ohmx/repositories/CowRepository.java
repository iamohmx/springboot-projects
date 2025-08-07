package com.iamohmx.ohmx.repositories;

import com.iamohmx.ohmx.entities.Cow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CowRepository extends JpaRepository<Cow, Long> {

    @Query("SELECT c FROM Cow c JOIN FETCH c.farm f JOIN FETCH f.owner WHERE f.id = :farmId")
    List<Cow> findByFarmIdWithDetails(@Param("farmId") Long farmId);

    @Query("SELECT c FROM Cow c JOIN FETCH c.farm f JOIN FETCH f.owner WHERE LOWER(c.cowName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Cow> findByCowNameContainingIgnoreCase(@Param("name") String name);

    @Query("SELECT c FROM Cow c JOIN FETCH c.farm f WHERE f.owner.id = :ownerId")
    List<Cow> findByOwnerId(@Param("ownerId") Long ownerId);
}
