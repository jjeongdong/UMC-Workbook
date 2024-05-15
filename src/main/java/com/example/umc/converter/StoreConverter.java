package com.example.umc.converter;

import com.example.umc.domain.Member;
import com.example.umc.domain.Mission;
import com.example.umc.domain.Review;
import com.example.umc.domain.mapping.MemberMission;
import com.example.umc.web.dto.MemberResponseDTO;
import com.example.umc.web.dto.StoreRequestDTO;
import com.example.umc.web.dto.StoreResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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


    public static StoreResponseDTO.MissionResponseDTO toMissionResponseDTO(Mission mission){
        return StoreResponseDTO.MissionResponseDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Mission toMission(StoreRequestDTO.MissionRequestDTO request){
        return Mission.builder()
                .reward(request.getReward())
                .criteria(request.getCriteria())
                .expirationDate(LocalDate.now().plus(1, ChronoUnit.WEEKS))
                .build();
    }

    public static StoreResponseDTO.MemberMissionResponseDTO toMemberMissionResponseDTO(MemberMission memberMission){
        return StoreResponseDTO.MemberMissionResponseDTO.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
