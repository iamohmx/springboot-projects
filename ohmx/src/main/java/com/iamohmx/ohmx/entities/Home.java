package com.iamohmx.ohmx.entities;

import lombok.Data;

@Data
public class Home {
    private String name;

    Home(String name) {
        this.name = name;
    }
}
