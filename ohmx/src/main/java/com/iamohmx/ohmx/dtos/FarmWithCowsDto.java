package com.iamohmx.ohmx.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmWithCowsDto {
    private Long id;
    private String name;
    private OwnerDto owner;
    private List<CowDto> cows;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OwnerDto {
        private Long id;
        private String name;
        private String surName;
        private String address;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CowDto {
        private Long id;
        private String cowName;
        private LocalDate birth;
        private String type;
    }
}
