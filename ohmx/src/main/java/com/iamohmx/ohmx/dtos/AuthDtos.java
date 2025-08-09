package com.iamohmx.ohmx.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

public class AuthDtos {

    @Data
    public static class RegisterRequest {
        @NotBlank
        @Size(min = 3, max = 50)
        private String username;
        @NotBlank
        @Email
        private String email;
        @NotBlank
        @Size(min = 6, max = 100)
        private String password;
        private Set<String> roles; // optional roles (e.g., ["ADMIN"]) else defaults to USER
    }

    @Data
    public static class LoginRequest {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

    @Data
    public static class AuthResponse {
        private String token;
        private String tokenType = "Bearer";
        private String username;
        private Set<String> roles;
    }
}
