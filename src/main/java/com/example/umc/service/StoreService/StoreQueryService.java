package com.example.umc.service.StoreService;


import com.example.umc.domain.Store;

import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);
}
