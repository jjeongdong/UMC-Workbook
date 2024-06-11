package com.example.umc.service.StoreService;


import com.example.umc.domain.Mission;
import com.example.umc.domain.Review;
import com.example.umc.domain.Store;
import com.example.umc.domain.mapping.MemberMission;
import com.example.umc.web.dto.StoreRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StoreCommandService {

    Store createStore(Long regionId, StoreRequestDTO.StoreCreateRequestDTO request);

//    Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewRequestDTO request, MultipartFile multipartFile) throws IOException;

    Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewRequestDTO request, List<MultipartFile> multipartFiles) throws IOException;

    Mission createMission(Long storeId, StoreRequestDTO.MissionRequestDTO request);

    MemberMission challengeMission(Long memberId, Long missionId);
}
