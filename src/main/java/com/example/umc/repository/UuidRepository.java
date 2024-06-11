package com.example.umc.repository;

import com.example.umc.domain.Uuid;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UuidRepository extends JpaRepository<Uuid, UUID> {
}
