package com.iamohmx.ohmx.services;

import com.iamohmx.ohmx.dtos.StudentDto;
import com.iamohmx.ohmx.entities.Student;
import com.iamohmx.ohmx.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AllUserService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }
//    public List<Student> findByName(String name) {
//        return studentRepository.findByNameContainingIgnoreCase(name);
//    }
//    public List<Student> findByEmail(String email) {
//        return studentRepository.findByEmailContainingIgnoreCase(email);
//    }
//    public List<Student> findByAge(int age) {
//        return studentRepository.findByAge(age);
//    }

    public StudentDto save(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student must not be null.");
        }
        if (student.getName() == null || student.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Student name must not be empty.");
        }
        if (student.getEmail() == null || student.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Student email must not be empty.");
        }
        if (student.getAge() <= 0) {
            throw new IllegalArgumentException("Student age must be greater than 0.");
        }
        Student savedStudent = studentRepository.save(student);
        StudentDto studentDto = new StudentDto();
        studentDto.setId(savedStudent.getId());
        studentDto.setName(savedStudent.getName());
        studentDto.setAge(savedStudent.getAge());
        studentDto.setEmail(savedStudent.getEmail());
        return studentDto;
    }

    public StudentDto update(Long id, Student student) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student with id " + id + " does not exist.");
        }
        if (student == null) {
            throw new IllegalArgumentException("Student must not be null.");
        }
        if (student.getName() == null || student.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Student name must not be empty.");
        }
        if (student.getEmail() == null || student.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Student email must not be empty.");
        }
        if (student.getAge() <= 0) {
            throw new IllegalArgumentException("Student age must be greater than 0.");
        }
        student.setId(id);
        Student updatedStudent = studentRepository.save(student);
        StudentDto studentDto = new StudentDto();
        studentDto.setId(updatedStudent.getId());
        studentDto.setName(updatedStudent.getName());
        studentDto.setAge(updatedStudent.getAge());
        studentDto.setEmail(updatedStudent.getEmail());
        return studentDto;
    }

    public void deleteById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student with id " + id + " does not exist.");
        }
        studentRepository.deleteById(id);
    }


    public boolean existsById(Long id) {
        return studentRepository.existsById(id);
    }
}
