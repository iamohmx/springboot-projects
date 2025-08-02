package com.iamohmx.ohmx.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Positive(message = "Age must be greater than 0")
    private int age;

    @NotBlank(message = "Email cannot be blank")
    private String email;
}
