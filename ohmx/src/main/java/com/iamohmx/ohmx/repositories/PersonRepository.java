package com.iamohmx.ohmx.repositories;

import com.iamohmx.ohmx.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByAgeGreaterThanEqualAndAgeLessThanEqual(int startAge, int endAge);

    // Search by name containing (case insensitive)
    List<Person> findByNameContainingIgnoreCase(String name);

    // Search by name and age range
    List<Person> findByNameContainingIgnoreCaseAndAgeGreaterThanEqualAndAgeLessThanEqual(
        String name, int startAge, int endAge);

    // Find all ordered by name ascending
    List<Person> findAllByOrderByNameAsc();

    // Find all ordered by name descending
    List<Person> findAllByOrderByNameDesc();

    // Find all ordered by id ascending
    List<Person> findAllByOrderByIdAsc();

    // Find all ordered by id descending
    List<Person> findAllByOrderByIdDesc();

    // Search by name with custom sorting
    @Query("SELECT p FROM Person p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) ORDER BY " +
           "CASE WHEN :sortBy = 'name' AND :sortDirection = 'asc' THEN p.name END ASC, " +
           "CASE WHEN :sortBy = 'name' AND :sortDirection = 'desc' THEN p.name END DESC, " +
           "CASE WHEN :sortBy = 'id' AND :sortDirection = 'asc' THEN p.id END ASC, " +
           "CASE WHEN :sortBy = 'id' AND :sortDirection = 'desc' THEN p.id END DESC")
    List<Person> findByNameContainingWithSort(@Param("name") String name,
                                             @Param("sortBy") String sortBy,
                                             @Param("sortDirection") String sortDirection);

    // Alternative simpler approach for name search with sorting
    @Query("SELECT p FROM Person p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Person> findByNameContainingIgnoreCaseOrderByIdAsc(@Param("name") String name);

    @Query("SELECT p FROM Person p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) ORDER BY p.id DESC")
    List<Person> findByNameContainingIgnoreCaseOrderByIdDesc(@Param("name") String name);

    @Query("SELECT p FROM Person p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) ORDER BY p.name ASC")
    List<Person> findByNameContainingIgnoreCaseOrderByNameAsc(@Param("name") String name);

    @Query("SELECT p FROM Person p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) ORDER BY p.name DESC")
    List<Person> findByNameContainingIgnoreCaseOrderByNameDesc(@Param("name") String name);
}
