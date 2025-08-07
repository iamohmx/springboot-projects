package com.iamohmx.ohmx.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "cows")
public class Cow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String cowName;

    private LocalDate birth;

    @NotEmpty
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id")
    @JsonBackReference("farm-cows")
    private Farm farm;
}
