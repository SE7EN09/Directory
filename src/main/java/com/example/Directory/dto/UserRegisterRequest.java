package com.example.Directory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserRegisterRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Pattern(
            regexp = "^\\+7(700|701|702|705|707|708|747|771|775|776|777|778)\\d{7}$",
            message = "Только казахстанские мобильные номера"
    )
    private String phone;

    private LocalDateTime birthDate;
}
