package com.training.learing.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Task {
    private Long id;
    private String name;
    private String description;
    private Boolean completed;
}
