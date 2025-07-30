package com.training.learing.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/hello")
public class HelloController {
    @GetMapping("/{name}")
    public ResponseEntity<String> hello(@PathVariable String name) {
        // Validate the name parameter
        if (name == null || name.isEmpty()) {
            return ResponseEntity.badRequest().body("Name cannot be null or empty");
        }
        if (name.length() > 50) {
            return ResponseEntity.badRequest().body("Name cannot exceed 50 characters");
        }
        if (!name.matches("^[a-zA-Z ]+$")) {
            return ResponseEntity.badRequest().body("Name can only contain letters and spaces");
        }
        String message = "Hello, " + name + "!";
        return ResponseEntity.ok(message);
    }
}
