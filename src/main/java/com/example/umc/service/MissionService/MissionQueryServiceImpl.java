package com.example.umc.service.MissionService;

import com.example.umc.domain.Mission;
import com.example.umc.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    public Optional<Mission> findMission(Long id) {
        return missionRepository.findById(id);
    }
}
