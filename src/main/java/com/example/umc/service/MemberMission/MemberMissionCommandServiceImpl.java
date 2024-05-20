package com.example.umc.service.MemberMission;

import com.example.umc.apiPayload.code.status.ErrorStatus;
import com.example.umc.apiPayload.exception.handler.FoodCategoryHandler;
import com.example.umc.apiPayload.exception.handler.MemberMissionHandler;
import com.example.umc.converter.FoodLikesConverter;
import com.example.umc.converter.MemberConverter;
import com.example.umc.domain.FoodCategory;
import com.example.umc.domain.Member;
import com.example.umc.domain.mapping.FoodLikes;
import com.example.umc.domain.mapping.MemberMission;
import com.example.umc.repository.FoodCategoryRepository;
import com.example.umc.repository.MemberMissionRepository;
import com.example.umc.repository.MemberRepository;
import com.example.umc.service.MemberService.MemberCommandService;
import com.example.umc.web.dto.MemberRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberMissionRepository memberMissionRepository;


    @Override
    public MemberMission completeMission(Long memberId, Long missionId) {
        Optional<MemberMission> memberMission = memberMissionRepository.findByMemberIdAndMissionId(memberId, missionId);

        if (memberMission.isEmpty()) {
            throw new MemberMissionHandler(ErrorStatus.MISSION_PROGRESS_NOT_YET);
        }

        memberMission.get().setStatus();

        return memberMission.get();
    }
}
