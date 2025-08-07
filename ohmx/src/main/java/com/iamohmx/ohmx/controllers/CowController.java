package com.iamohmx.ohmx.controllers;

import com.iamohmx.ohmx.dtos.CowWithDetailsDto;
import com.iamohmx.ohmx.dtos.FarmWithCowsDto;
import com.iamohmx.ohmx.entities.Cow;
import com.iamohmx.ohmx.entities.Farm;
import com.iamohmx.ohmx.repositories.CowRepository;
import com.iamohmx.ohmx.repositories.FarmRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CowController {

    @Autowired
    private CowRepository cowRepository;

    @Autowired
    private FarmRepository farmRepository;

    @GetMapping("/getCowList/{farm}")
    public ResponseEntity<FarmWithCowsDto> getCowListByFarm(@PathVariable Long farm) {
        Optional<Farm> farmOpt = farmRepository.findByIdWithOwnerAndCows(farm);

        if (farmOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Farm farmEntity = farmOpt.get();
        FarmWithCowsDto farmDto = convertFarmToDto(farmEntity);

        return ResponseEntity.ok(farmDto);
    }

    private FarmWithCowsDto convertFarmToDto(Farm farm) {
        FarmWithCowsDto.OwnerDto ownerDto = new FarmWithCowsDto.OwnerDto(
            farm.getOwner().getId(),
            farm.getOwner().getName(),
            farm.getOwner().getSurName(),
            farm.getOwner().getAddress()
        );

        List<FarmWithCowsDto.CowDto> cowDtos = farm.getCows().stream()
            .map(cow -> new FarmWithCowsDto.CowDto(
                cow.getId(),
                cow.getCowName(),
                cow.getBirth(),
                cow.getType()
            ))
            .collect(Collectors.toList());

        return new FarmWithCowsDto(
            farm.getId(),
            farm.getName(),
            ownerDto,
            cowDtos
        );
    }

    @PostMapping("/cowSearch")
    public ResponseEntity<List<CowWithDetailsDto>> searchCowsByName(@RequestBody CowSearchRequest request) {
        List<Cow> cows = cowRepository.findByCowNameContainingIgnoreCase(request.getName());

        List<CowWithDetailsDto> cowDtos = cows.stream()
            .map(this::convertCowToDto)
            .collect(Collectors.toList());

        return ResponseEntity.ok(cowDtos);
    }

    private CowWithDetailsDto convertCowToDto(Cow cow) {
        CowWithDetailsDto.OwnerDto ownerDto = new CowWithDetailsDto.OwnerDto(
            cow.getFarm().getOwner().getId(),
            cow.getFarm().getOwner().getName(),
            cow.getFarm().getOwner().getSurName(),
            cow.getFarm().getOwner().getAddress()
        );

        CowWithDetailsDto.FarmDto farmDto = new CowWithDetailsDto.FarmDto(
            cow.getFarm().getId(),
            cow.getFarm().getName(),
            ownerDto
        );

        return new CowWithDetailsDto(
            cow.getId(),
            cow.getCowName(),
            cow.getBirth(),
            cow.getType(),
            farmDto
        );
    }

    // DTO for search request
    @Data
    public static class CowSearchRequest {
        private String name;
    }
}
