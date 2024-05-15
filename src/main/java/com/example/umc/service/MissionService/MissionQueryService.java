package com.example.umc.service.MissionService;

import com.example.umc.domain.Mission;

import java.util.Optional;

public interface MissionQueryService {

    Optional<Mission> findMission(Long id);
}
