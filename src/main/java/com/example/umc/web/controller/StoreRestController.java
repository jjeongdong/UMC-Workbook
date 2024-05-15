package com.example.umc.web.controller;

import com.example.umc.apiPayload.ApiResponse;
import com.example.umc.converter.StoreConverter;
import com.example.umc.domain.Mission;
import com.example.umc.domain.Review;
import com.example.umc.domain.mapping.MemberMission;
import com.example.umc.service.StoreService.StoreCommandService;
import com.example.umc.validation.annotation.ExistMember;
import com.example.umc.validation.annotation.ExistMission;
import com.example.umc.validation.annotation.ExistStore;
import com.example.umc.web.dto.StoreRequestDTO;
import com.example.umc.web.dto.StoreResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<StoreResponseDTO.ReviewResponseDTO> createReview(@RequestBody @Valid StoreRequestDTO.ReviewRequestDTO request,
                                                                        @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                        @ExistMember @RequestParam(name = "memberId") Long memberId) {
        Review review = storeCommandService.createReview(memberId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toReviewResponseDTO(review));
    }

    @PostMapping("/{storeId}/missions")
    public ApiResponse<StoreResponseDTO.MissionResponseDTO> createReview(@RequestBody @Valid StoreRequestDTO.MissionRequestDTO request,
                                                                         @ExistStore @PathVariable(name = "storeId") Long storeId) {
        Mission mission = storeCommandService.createMission(storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toMissionResponseDTO(mission));
    }

    @PostMapping("/missions/{missionId}/challenge")
    public ApiResponse<StoreResponseDTO.MemberMissionResponseDTO> createReview(@ExistMission @PathVariable(name = "missionId") Long missionId,
                                                                               @ExistMember @RequestParam(name = "memberId") Long memberId) {

        MemberMission memberMission = storeCommandService.challengeMission(memberId, missionId);
        return ApiResponse.onSuccess(StoreConverter.toMemberMissionResponseDTO(memberMission));
    }
}
