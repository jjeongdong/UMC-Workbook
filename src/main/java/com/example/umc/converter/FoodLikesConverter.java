package com.example.umc.converter;

import com.example.umc.domain.FoodCategory;
import com.example.umc.domain.mapping.FoodLikes;

import java.util.List;
import java.util.stream.Collectors;

public class FoodLikesConverter {
    public static List<FoodLikes> toFoodLikesList(List<FoodCategory> foodCategoryList){

        return foodCategoryList.stream()
                .map(foodCategory ->
                        FoodLikes.builder()
                                .foodCategory(foodCategory)
                                .build()
                ).collect(Collectors.toList());
    }
}
