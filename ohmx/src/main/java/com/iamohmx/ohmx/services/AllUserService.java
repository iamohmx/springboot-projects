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
}
