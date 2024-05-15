package com.example.umc.service.RegionService;

import com.example.umc.domain.Region;

import java.util.Optional;

public interface RegionQueryService {

    Optional<Region> findRegion(Long id);
}
