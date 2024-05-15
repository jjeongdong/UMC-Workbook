package com.example.umc.converter;

import com.example.umc.domain.Member;
import com.example.umc.domain.Review;
import com.example.umc.web.dto.MemberResponseDTO;
import com.example.umc.web.dto.StoreRequestDTO;
import com.example.umc.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

public class StoreConverter {

    public static StoreResponseDTO.ReviewResponseDTO toReviewResponseDTO(Review review){
        return StoreResponseDTO.ReviewResponseDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(StoreRequestDTO.ReviewRequestDTO request){
        return Review.builder()
                .rating(request.getRating())
                .content(request.getContent())
                .build();
    }

}
