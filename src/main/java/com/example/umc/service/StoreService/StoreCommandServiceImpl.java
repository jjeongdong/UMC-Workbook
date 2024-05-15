package com.example.umc.service.StoreService;

import com.example.umc.apiPayload.code.status.ErrorStatus;
import com.example.umc.apiPayload.exception.handler.MissionHandler;
import com.example.umc.apiPayload.exception.handler.TempHandler;
import com.example.umc.converter.StoreConverter;
import com.example.umc.domain.Member;
import com.example.umc.domain.Mission;
import com.example.umc.domain.Review;
import com.example.umc.domain.mapping.MemberMission;
import com.example.umc.repository.*;
import com.example.umc.web.dto.StoreRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService{

    private final ReviewRepository reviewRepository;

    private final MemberRepository memberRepository;

    private final StoreRepository storeRepository;

    private final MissionRepository missionRepository;

    private final MemberMissionRepository memberMissionRepository;


    @Override
    public Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewRequestDTO request) {

        Review review = StoreConverter.toReview(request);

        review.setMember(memberRepository.findById(memberId).get());
        review.setStore(storeRepository.findById(storeId).get());

        return reviewRepository.save(review);
    }

    @Override
    public Mission createMission(Long storeId, StoreRequestDTO.MissionRequestDTO request) {

        Mission mission = StoreConverter.toMission(request);

        mission.setStore(storeRepository.findById(storeId).get());

        return missionRepository.save(mission);
    }

    @Override
    public MemberMission challengeMission(Long memberId, Long missionId) {

        Member member = memberRepository.findById(memberId).get();
        Mission mission = missionRepository.findById(missionId).get();

        if (memberMissionRepository.existsByMemberAndMission(member, mission)) {
            throw new MissionHandler(ErrorStatus.MISSION_ALREADY_IN_PROGRESS);
        }

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .build();

        return memberMissionRepository.save(memberMission);
    }
}
