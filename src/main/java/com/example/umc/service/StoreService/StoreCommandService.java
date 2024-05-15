package com.example.umc.service.StoreService;


import com.example.umc.domain.Mission;
import com.example.umc.domain.Review;
import com.example.umc.web.dto.StoreRequestDTO;

public interface StoreCommandService {

    Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewRequestDTO request);
    Mission createMission(Long storeId, StoreRequestDTO.MissionRequestDTO request);
}
