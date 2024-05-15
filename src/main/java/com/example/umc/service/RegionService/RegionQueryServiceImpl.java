package com.example.umc.service.RegionService;

import com.example.umc.domain.Mission;
import com.example.umc.domain.Region;
import com.example.umc.repository.MissionRepository;
import com.example.umc.repository.RegionRepository;
import com.example.umc.service.MissionService.MissionQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegionQueryServiceImpl implements RegionQueryService {

    private final RegionRepository regionRepository;

    @Override
    public Optional<Region> findRegion(Long id) {
        return regionRepository.findById(id);
    }
}
