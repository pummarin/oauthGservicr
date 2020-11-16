package com.example.voting.entity.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank
    private String studentId;

    @NotBlank
    private String identifyNumber;
}
