package com.example.umc.converter;

import com.example.umc.domain.Member;
import com.example.umc.domain.Review;
import com.example.umc.domain.enums.Gender;
import com.example.umc.domain.mapping.MemberMission;
import com.example.umc.web.dto.MemberRequestDTO;
import com.example.umc.web.dto.MemberResponseDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDto request){

        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .foodLikesList(new ArrayList<>())
                .build();
    }

    public static MemberResponseDTO.ReviewPreViewDTO toReviewPreViewDTO(Review review) {
        return MemberResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt().toLocalDate())
                .content(review.getContent())
                .build();
    }

    public static MemberResponseDTO.ReviewPreViewListDTO toReviewPreViewListDTO(Page<Review> reviewList) {

        List<MemberResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(MemberConverter::toReviewPreViewDTO).collect(Collectors.toList());

        return MemberResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static MemberResponseDTO.ChallengingMissionPreViewDTO toChallengingMissionPreViewDTO(MemberMission memberMission) {
        return MemberResponseDTO.ChallengingMissionPreViewDTO.builder()
                .reward(memberMission.getMission().getReward())
                .criteria(memberMission.getMission().getCriteria())
                .expirationDate(memberMission.getMission().getExpirationDate())
                .storeName(memberMission.getMission().getStore().getName())
                .build();
    }

    public static MemberResponseDTO.ChallengingMissionPreViewListDTO toChallengingMissionPreViewListDTO(Page<MemberMission> challengingMissionList) {

        List<MemberResponseDTO.ChallengingMissionPreViewDTO> reviewPreViewDTOList = challengingMissionList.stream()
                .map(MemberConverter::toChallengingMissionPreViewDTO).collect(Collectors.toList());

        return MemberResponseDTO.ChallengingMissionPreViewListDTO.builder()
                .isLast(challengingMissionList.isLast())
                .isFirst(challengingMissionList.isFirst())
                .totalPage(challengingMissionList.getTotalPages())
                .totalElements(challengingMissionList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .challengingMissionList(reviewPreViewDTOList)
                .build();
    }

    public static MemberResponseDTO.MissionCompleteResponseDTO toMissionCompleteResponseDTO(MemberMission memberMission){
        return MemberResponseDTO.MissionCompleteResponseDTO.builder()
                .missionId(memberMission.getMission().getId())
                .missionStatus(memberMission.getStatus())
                .finishedAt(memberMission.getFinishedAt())
                .build();
    }
}
