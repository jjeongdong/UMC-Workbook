package com.example.umc.service.MissionService;

import com.example.umc.domain.Mission;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface MissionQueryService {

    Optional<Mission> findMission(Long id);

    Page<Mission> getMissionList(Long storeId, Integer page);
}
