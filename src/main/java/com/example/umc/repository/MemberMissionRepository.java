package com.example.umc.repository;

import com.example.umc.domain.Member;
import com.example.umc.domain.Mission;
import com.example.umc.domain.mapping.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    boolean existsByMemberAndMission(Member member, Mission mission);
}
