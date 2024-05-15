package com.example.umc.web.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;


public class StoreRequestDTO {

    @Getter
    public static class ReviewRequestDTO{
        @NotNull
        @DecimalMin(value = "0.0")
        @DecimalMax(value = "5.0")
        Float rating;

        @NotBlank
        @Size(max = 200)
        String content;
    }
}
