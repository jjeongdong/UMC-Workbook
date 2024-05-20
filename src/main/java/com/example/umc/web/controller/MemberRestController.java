package com.example.umc.web.controller;

import com.example.umc.apiPayload.ApiResponse;
import com.example.umc.converter.MemberConverter;
import com.example.umc.domain.Member;
import com.example.umc.domain.Review;
import com.example.umc.domain.mapping.MemberMission;
import com.example.umc.service.MemberMission.MemberMissionCommandService;
import com.example.umc.service.MemberService.MemberCommandService;
import com.example.umc.service.MemberService.MemberQueryService;
import com.example.umc.validation.annotation.ExistMember;
import com.example.umc.validation.annotation.ExistMission;
import com.example.umc.web.dto.MemberRequestDTO;
import com.example.umc.web.dto.MemberResponseDTO;
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

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;

    private final MemberQueryService memberQueryService;

    private final MemberMissionCommandService memberMissionCommandService;


    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    // 특정 멤버의 리뷰 목록 조회
    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "특정 멤버의 리뷰 목록 조회 API",description = "특정 멤버 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다.")
    })
    public ApiResponse<MemberResponseDTO.ReviewPreViewListDTO> getReviewListByMember(@ExistMember @PathVariable(name = "memberId") Long memberId,
                                                                                     @RequestParam(name = "page") Integer page){

        Page<Review> reviewList = memberQueryService.getReviewList(memberId, page);
        return ApiResponse.onSuccess(MemberConverter.toReviewPreViewListDTO(reviewList));
    }

    // 특정 멤버의 진행중인 미션 목록 조회
    @GetMapping("/{memberId}/challenging-missions")
    @Operation(summary = "특정 멤버의 진행중인 미션 목록 조회 API",description = "특정 멤버 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다.")
    })
    public ApiResponse<MemberResponseDTO.ChallengingMissionPreViewListDTO> getChallengingMissionList(@ExistMember @PathVariable(name = "memberId") Long memberId,
                                                                                                     @RequestParam(name = "page") Integer page){

        Page<MemberMission> challengingMissionList = memberQueryService.getChallengingMissionList(memberId, page);
        return ApiResponse.onSuccess(MemberConverter.toChallengingMissionPreViewListDTO(challengingMissionList));
    }

    //  진행중인 미션 진행 완료로 바꾸기 API 구현
    @PatchMapping("/{memberId}/member-missions/{missionId}")
    @Operation(summary = "진행중인 미션 진행 완료로 바꾸기 API",description = "진행중인 미션 진행 완료로 바꾸기 API이며, 미션 완료하실 missionId를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!"),
            @Parameter(name = "missionId", description = "미션의 아이디, path variable 입니다!")
    })
    public ApiResponse<MemberResponseDTO.MissionCompleteResponseDTO> completeMission(@ExistMember @PathVariable(name = "memberId") Long memberId,
                                                                                     @ExistMission @PathVariable(name = "missionId") Long missionId){

        MemberMission memberMission = memberMissionCommandService.completeMission(memberId, missionId);
        return ApiResponse.onSuccess(MemberConverter.toMissionCompleteResponseDTO(memberMission));
    }
}
