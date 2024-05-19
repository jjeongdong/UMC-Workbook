package com.example.umc.service.MemberService;


import com.example.umc.domain.Member;
import com.example.umc.domain.Mission;
import com.example.umc.domain.Review;
import com.example.umc.domain.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface MemberQueryService {

    Optional<Member> findMember(Long id);

    Page<Review> getReviewList(Long memberId, Integer page);

    Page<MemberMission> getChallengingMissionList(Long memberId, Integer page);
}
