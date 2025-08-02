package com.iamohmx.ohmx.repositories;

import com.iamohmx.ohmx.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByEmailContainingIgnoreCase(String email);

    List<Student> findByAge(int age);

    List<Student> findAll();
}
