package com.example.umc.web.controller;

import com.example.umc.apiPayload.ApiResponse;
import com.example.umc.converter.StoreConverter;
import com.example.umc.domain.Mission;
import com.example.umc.domain.Review;
import com.example.umc.domain.Store;
import com.example.umc.domain.mapping.MemberMission;
import com.example.umc.service.MissionService.MissionQueryService;
import com.example.umc.service.StoreService.StoreCommandService;
import com.example.umc.service.StoreService.StoreQueryService;
import com.example.umc.validation.annotation.ExistMember;
import com.example.umc.validation.annotation.ExistMission;
import com.example.umc.validation.annotation.ExistRegion;
import com.example.umc.validation.annotation.ExistStore;
import com.example.umc.web.dto.StoreRequestDTO;
import com.example.umc.web.dto.StoreResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    private final StoreQueryService storeQueryService;

    private final MissionQueryService missionQueryService;

    @PostMapping("/{regionId}")
    public ApiResponse<StoreResponseDTO.StoreCreateResponseDTO> createStore(@RequestBody @Valid StoreRequestDTO.StoreCreateRequestDTO request,
                                                                            @ExistRegion @PathVariable(name = "regionId") Long regionId) {
        Store store = storeCommandService.createStore(regionId, request);
        return ApiResponse.onSuccess(StoreConverter.toStoreCreateResponseDTO(store));
    }

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

    // 특정 가게의 리뷰 목록 조회 API
    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다.")
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewListByStore(@ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                                   @RequestParam(name = "page") Integer page) {

        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(StoreConverter.toReviewPreViewListDTO(reviewList));
    }

    // 특정 가게의 미션 목록 조회 API
    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다.")
    })
    public ApiResponse<StoreResponseDTO.MissionPreViewListDTO> getMissionList(@ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                              @RequestParam(name = "page", defaultValue = "0") Integer page ) {


        Page<Mission> missionList = missionQueryService.getMissionList(storeId, page);
        return ApiResponse.onSuccess(StoreConverter.toMissionPreViewListDTO(missionList));
    }
}
