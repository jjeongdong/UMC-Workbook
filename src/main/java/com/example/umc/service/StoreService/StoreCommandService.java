package com.example.umc.service.StoreService;


import com.example.umc.domain.Mission;
import com.example.umc.domain.Review;
import com.example.umc.domain.Store;
import com.example.umc.domain.mapping.MemberMission;
import com.example.umc.web.dto.StoreRequestDTO;

public interface StoreCommandService {

    Store createStore(Long regionId, StoreRequestDTO.StoreCreateRequestDTO request);

    Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewRequestDTO request);

    Mission createMission(Long storeId, StoreRequestDTO.MissionRequestDTO request);

    MemberMission challengeMission(Long memberId, Long missionId);
}
