package com.training.learing.controllers;

import com.training.learing.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @GetMapping("/")
    public ResponseEntity<?> getAllTasks() {
        // This method should return a list of tasks
        // For now, we will return an empty response
        return ResponseEntity.status(HttpStatus.OK).body("List of tasks will be here");
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        // This method should return a task by its ID
        // For now, we will return an empty response
        return ResponseEntity.status(HttpStatus.OK).body("Task with ID " + id + " will be here");
    }

    @GetMapping("/task")
    public ResponseEntity<?> getTaskByName(@RequestParam(name = "name") String name) {
        // This method should return a task by its name
        // For now, we will return an empty response
        return ResponseEntity.status(HttpStatus.OK).body("Task with name " + name + " will be here");
    }

    @GetMapping("/completed")
    public ResponseEntity<?> getCompletedTasks() {
        // This method should return a list of completed tasks
        // For now, we will return an empty response
        return ResponseEntity.status(HttpStatus.OK).body("List of completed tasks will be here");
    }

    @GetMapping("/incomplete")
    public ResponseEntity<?> getIncompleteTasks() {
        // This method should return a list of incomplete tasks
        // For now, we will return an empty response
        return ResponseEntity.status(HttpStatus.OK).body("List of incomplete tasks will be here");
    }

    @PostMapping("/")
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        // This method should create a new task
        // For now, we will return an empty response
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody String task) {
        // This method should update an existing task by its ID
        // For now, we will return an empty response
        return ResponseEntity.status(HttpStatus.OK).body("Task with ID " + id + " updated: " + task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        // This method should delete a task by its ID
        // For now, we will return an empty response
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Task with ID " + id + " deleted");
    }
}
