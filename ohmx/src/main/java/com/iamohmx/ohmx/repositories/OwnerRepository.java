package com.iamohmx.ohmx.repositories;

import com.iamohmx.ohmx.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    @Query("SELECT DISTINCT o FROM Owner o JOIN FETCH o.farms WHERE o.id = :ownerId")
    Optional<Owner> findByIdWithFarms(@Param("ownerId") Long ownerId);
}
