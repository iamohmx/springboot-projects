package com.iamohmx.ohmx.controllers;


import com.iamohmx.ohmx.entities.Person;
import com.iamohmx.ohmx.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PersonController {
    @Autowired
    PersonRepository personRepository;

    @GetMapping("/filter-by-age")
    public ResponseEntity<?> findByAgeGreaterThanEqual(@RequestParam int startAge, @RequestParam int endAge) {
        List<Person> people = personRepository.findByAgeGreaterThanEqualAndAgeLessThanEqual(startAge, endAge);
        if (people == null || people.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "ไม่พบข้อมูลในช่วงอายุที่ระบุ");
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.ok().body(people);
        }
    }

    @GetMapping("/search-by-name")
    public ResponseEntity<?> searchByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        List<Person> people;

        // Use simpler, more reliable query methods
        if ("name".equals(sortBy)) {
            if ("desc".equals(sortDirection)) {
                people = personRepository.findByNameContainingIgnoreCaseOrderByNameDesc(name);
            } else {
                people = personRepository.findByNameContainingIgnoreCaseOrderByNameAsc(name);
            }
        } else {
            if ("desc".equals(sortDirection)) {
                people = personRepository.findByNameContainingIgnoreCaseOrderByIdDesc(name);
            } else {
                people = personRepository.findByNameContainingIgnoreCaseOrderByIdAsc(name);
            }
        }

        if (people == null || people.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "ไม่พบข้อมูลที่มีชื่อ: " + name);
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.ok().body(people);
        }
    }

    @GetMapping("/search-by-name-and-age")
    public ResponseEntity<?> searchByNameAndAge(
            @RequestParam String name,
            @RequestParam int startAge,
            @RequestParam int endAge,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        List<Person> people = personRepository.findByNameContainingIgnoreCaseAndAgeGreaterThanEqualAndAgeLessThanEqual(
                name, startAge, endAge);

        if (people == null || people.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "ไม่พบข้อมูลที่มีชื่อ: " + name + " และอายุระหว่าง " + startAge + "-" + endAge);
            return ResponseEntity.ok().body(response);
        } else {
            // Sort the filtered results in memory
            if ("name".equals(sortBy)) {
                if ("desc".equals(sortDirection)) {
                    people.sort((p1, p2) -> p2.getName().compareToIgnoreCase(p1.getName()));
                } else {
                    people.sort((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
                }
            } else if ("id".equals(sortBy)) {
                if ("desc".equals(sortDirection)) {
                    people.sort((p1, p2) -> p2.getId().compareTo(p1.getId()));
                } else {
                    people.sort((p1, p2) -> p1.getId().compareTo(p2.getId()));
                }
            }
            return ResponseEntity.ok().body(people);
        }
    }

    @GetMapping("/all-persons")
    public ResponseEntity<?> getAllPersons(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        List<Person> people;

        if ("name".equals(sortBy)) {
            if ("desc".equals(sortDirection)) {
                people = personRepository.findAllByOrderByNameDesc();
            } else {
                people = personRepository.findAllByOrderByNameAsc();
            }
        } else {
            if ("desc".equals(sortDirection)) {
                people = personRepository.findAllByOrderByIdDesc();
            } else {
                people = personRepository.findAllByOrderByIdAsc();
            }
        }

        if (people == null || people.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "ไม่พบข้อมูลในระบบ");
            return ResponseEntity.ok().body(new HashMap<String, String>() {{
                put("message", "ไม่พบข้อมูลในช่วงอายุที่ระบุ");
            }});
        } else {
            return ResponseEntity.ok().body(people);
        }
    }
}
