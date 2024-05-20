package com.example.umc.service.MemberMission;

import com.example.umc.domain.mapping.MemberMission;

public interface MemberMissionCommandService {

    MemberMission completeMission(Long memberId, Long missionId);
}
