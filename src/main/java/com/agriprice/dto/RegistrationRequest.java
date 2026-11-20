package com.agriprice.dto;
import jakarta.validation.constraints.*;
public record RegistrationRequest(@NotBlank @Size(max=80) String fullName, @Email @NotBlank String email,
 @NotBlank @Size(min=8,max=72) String password, @Size(max=20) String phone) {}
