package com.iamohmx.ohmx.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerWithCowsDto {
    private Long id;
    private String name;
    private String surName;
    private String address;
    private List<CowDetailDto> cows;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CowDetailDto {
        private Long id;
        private String cowName;
        private LocalDate birth;
        private String type;
        private String farmName;
    }
}
