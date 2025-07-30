package com.iamohmx.ohmx.entities;

import lombok.Data;

@Data
public class Student {
    private String name;
    private int age;
    private String email;

    public Student(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
