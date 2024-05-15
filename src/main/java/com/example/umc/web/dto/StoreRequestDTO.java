package com.example.umc.web.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;


public class StoreRequestDTO {

    @Getter
    public static class StoreCreateRequestDTO{

        @NotNull
        private String name;

        @NotNull
        private String address;

        @NotNull
        private Float rating;

        @NotNull
        private String category;

    }

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

    @Getter
    public static class MissionRequestDTO{
        @NotNull
        @Min(value = 0)
        @Max(value = 999)
        Integer reward;

        @NotNull
        @Min(value = 0)
        @Max(value = 50000)
        Integer criteria;
    }
}
