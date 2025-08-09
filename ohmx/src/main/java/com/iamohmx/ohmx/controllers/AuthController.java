package com.iamohmx.ohmx.controllers;

import com.iamohmx.ohmx.dtos.AuthDtos;
import com.iamohmx.ohmx.entities.AppUser;
import com.iamohmx.ohmx.services.JwtService;
import com.iamohmx.ohmx.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthDtos.RegisterRequest request) {
        try {
            AppUser user = userService.register(request.getUsername(), request.getEmail(), request.getPassword(),
                    request.getRoles());
            AuthDtos.AuthResponse response = new AuthDtos.AuthResponse();
            response.setUsername(user.getUsername());
            response.setRoles(user.getRoles());
            response.setToken(jwtService.generateToken(user.getUsername(), user.getRoles()));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"error\":\"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"Registration failed\"}");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthDtos.LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            var userDetails = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            var roles = userDetails.getAuthorities().stream().map(a -> a.getAuthority().replace("ROLE_", ""))
                    .collect(java.util.stream.Collectors.toSet());
            AuthDtos.AuthResponse response = new AuthDtos.AuthResponse();
            response.setUsername(userDetails.getUsername());
            response.setRoles(roles);
            response.setToken(jwtService.generateToken(userDetails.getUsername(), roles));
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\":\"Invalid credentials\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"Login failed\"}");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\":\"Unauthorized\"}");
        }
        return ResponseEntity.ok(java.util.Map.of(
                "username", authentication.getName(),
                "roles", authentication.getAuthorities().stream().map(a -> a.getAuthority()).toList()));
    }
}
