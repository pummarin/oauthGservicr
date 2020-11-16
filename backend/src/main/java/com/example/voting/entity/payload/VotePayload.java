package com.example.voting.entity.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class VotePayload {

    @NotBlank
    private Long students_id;

    @NotBlank
    private Long can_id;

    @NotNull
    private String hash;
}
