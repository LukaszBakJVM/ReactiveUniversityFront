package org.example.reactiveuniversityfront.register.dto;

public record RegistrationRequestDto(String firstName, String lastName, String email, String password, String role) {
}
