package com.iamohmx.ohmx.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class Student {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Positive(message = "Age must be greater than 0")
    private int age;

    @NotBlank(message = "Email cannot be blank")
    private String email;

    public Student(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
