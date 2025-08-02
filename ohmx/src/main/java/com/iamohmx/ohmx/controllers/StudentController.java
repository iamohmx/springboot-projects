package com.iamohmx.ohmx.controllers;

import com.iamohmx.ohmx.dtos.StudentDto;
import com.iamohmx.ohmx.entities.Student;
import com.iamohmx.ohmx.services.AllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    AllUserService studentService;

    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> students = studentService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }
    @PostMapping("/")
    public ResponseEntity<StudentDto> createStudent(@RequestBody Student student) {
        if (student == null || student.getName() == null || student.getEmail() == null || student.getAge() <= 0) {
            return ResponseEntity.badRequest().build();
        }
        StudentDto savedStudent = studentService.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        if (student == null || student.getName() == null || student.getEmail() == null || student.getAge() <= 0) {
            return ResponseEntity.badRequest().build();
        }
        StudentDto updatedStudent = studentService.update(id, student);
        return ResponseEntity.ok(updatedStudent);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (!studentService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

