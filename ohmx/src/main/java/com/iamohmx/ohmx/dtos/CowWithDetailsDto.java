package com.iamohmx.ohmx.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CowWithDetailsDto {
    private Long id;
    private String cowName;
    private LocalDate birth;
    private String type;
    private FarmDto farm;

    // Nested DTOs
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FarmDto {
        private Long id;
        private String name;
        private OwnerDto owner;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OwnerDto {
        private Long id;
        private String name;
        private String surName;
        private String address;
    }
}
