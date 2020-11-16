package com.example.voting.entity.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FindStudent {

    @NotNull
    private Long student_id;
}
