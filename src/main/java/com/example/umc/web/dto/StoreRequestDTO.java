package com.example.umc.web.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;


public class StoreRequestDTO {

    @Getter
    public static class StoreCreateRequestDTO{

        @NotNull
        String name;

        @NotNull
        String address;

        @NotNull
        Float rating;

        @NotNull
        String category;

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
