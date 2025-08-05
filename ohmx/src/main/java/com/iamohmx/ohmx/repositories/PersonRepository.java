package com.iamohmx.ohmx.repositories;

import com.iamohmx.ohmx.entities.Person;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByAgeGreaterThanEqualAndAgeLessThanEqual(int startAge, int endAge);
}
