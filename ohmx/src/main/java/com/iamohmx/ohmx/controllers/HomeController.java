package com.iamohmx.ohmx.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
//@RequestMapping("")
public class HomeController {

    @PostMapping("/hello")
    public ResponseEntity<?> hello(@RequestBody Map<String, String> body) {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", body.get("name")));
    }
}
