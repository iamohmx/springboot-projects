package com.iamohmx.ohmx.controllers;

import com.iamohmx.ohmx.entities.Student;
import com.iamohmx.ohmx.services.AllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    AllUserService studentService;

    @GetMapping("/")
    public ResponseEntity<?> home() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }
}
