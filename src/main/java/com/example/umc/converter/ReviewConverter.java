package com.example.umc.converter;

import com.example.umc.domain.Review;
import com.example.umc.domain.ReviewImg;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static List<ReviewImg> toReviewImgList(List<String> pictureUrls, Review review) {
        return pictureUrls.stream()
                .map(url -> toReviewImg(url, review))
                .collect(Collectors.toList());
    }


    public static ReviewImg toReviewImg(String pictureUrl, Review review) {
        return ReviewImg.builder()
                .imgUrl(pictureUrl)
                .review(review)
                .build();
    }
}
