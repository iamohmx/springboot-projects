package com.iamohmx.ohmx.controllers;

import com.iamohmx.ohmx.entities.Student;
import com.iamohmx.ohmx.services.AllUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

    @Autowired
    AllUserService studentService;

    @GetMapping("/")
    public ResponseEntity<?> home() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }

    @GetMapping("/getStudents")
    public ResponseEntity<?> searchStudent(@RequestParam Map<String, Object> requestBody) {
        String name = (String) requestBody.get("name");
        String ageStr = (String) requestBody.get("age");
        Integer age = null;
        if (ageStr != null && !ageStr.isEmpty()) {
            try {
                age = Integer.parseInt(ageStr);
            } catch (NumberFormatException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Invalid age format"));
            }
        }
        String email = (String) requestBody.get("email");
        List<Student> students = studentService.searchStudents(name, age, email);
        if (students == null || students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No students found"));
        }
        Object response = Map.of(
                "message", "Search completed",
                "students", students
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/add-student-2")
    public ResponseEntity<?> addStudent2(@Valid @RequestBody List<Student> studentDataList) {
        List<Student> students = studentDataList;
        Object response = Map.of(
                "message", "Students added successfully",
                "students", students
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/add-student-3/{name}/{age}/{email}")
    public ResponseEntity<?> addStudent3(
            @PathVariable String name,
            @PathVariable int age,
            @PathVariable String email
    ) {
        Student student = new Student(name, age, email);
        Object response = Map.of(
                "message", "Student added successfully",
                "student", student
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
