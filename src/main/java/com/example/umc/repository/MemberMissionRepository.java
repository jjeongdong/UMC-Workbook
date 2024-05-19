package com.example.umc.repository;

import com.example.umc.domain.Member;
import com.example.umc.domain.Mission;
import com.example.umc.domain.enums.MissionStatus;
import com.example.umc.domain.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    boolean existsByMemberAndMission(Member member, Mission mission);

    @EntityGraph(attributePaths = {"member", "mission.store"})
    @Query("SELECT mm FROM MemberMission mm WHERE mm.member = :member AND mm.status = :status")
    Page<MemberMission> findByMemberAndStatus(@Param("member") Member member, @Param("status") MissionStatus status, PageRequest pageRequest);
}
