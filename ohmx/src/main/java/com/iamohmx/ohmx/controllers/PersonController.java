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

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/filter-by-age")
    public ResponseEntity<?> findByAgeGreaterThanEqual(@RequestParam int startAge, @RequestParam int endAge) {
        List<Person> people = personRepository.findByAgeGreaterThanEqualAndAgeLessThanEqual(startAge, endAge);
        if (people == null || people.isEmpty()) {
        } else {
            return ResponseEntity.ok().body(people);
        }
    }
}
