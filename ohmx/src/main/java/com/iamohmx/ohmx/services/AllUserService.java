package com.iamohmx.ohmx.services;

import com.iamohmx.ohmx.entities.Home;
import com.iamohmx.ohmx.entities.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllUserService {

    public List<Student> getAllStudents() {
        return List.of(
                new Student("John Doe", 20, "john@doe.com"),
                new Student("Jane Smith", 22, "jane@smith"),
                new Student("Alice Johnson", 19, "alice@john.com")
        );
    }

    public List<Student> searchStudents(String name, Integer age, String email) {
        // Mock student data
        List<Student> allStudents = List.of(
                new Student("John Doe", 20, "john@doe.com"),
                new Student("Jane Smith", 22, "jane@smith"),
                new Student("Alice Johnson", 19, "alice@john.com"),
                new Student("Bob Brown", 20, "bob@brown.com")
        );

        // Filter by name, age, email if provided
        return allStudents.stream()
                .filter(s -> name == null || name.isEmpty() || s.getName().equalsIgnoreCase(name))
                .filter(s -> age == null || s.getAge() == age)
                .filter(s -> email == null || email.isEmpty() || s.getEmail().equalsIgnoreCase(email))
                .toList();
    }
}
