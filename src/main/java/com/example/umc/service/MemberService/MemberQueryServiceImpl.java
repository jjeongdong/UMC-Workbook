package com.example.umc.service.MemberService;

import com.example.umc.domain.Member;
import com.example.umc.domain.Mission;
import com.example.umc.domain.Review;
import com.example.umc.domain.enums.MissionStatus;
import com.example.umc.domain.mapping.MemberMission;
import com.example.umc.repository.MemberMissionRepository;
import com.example.umc.repository.MemberRepository;
import com.example.umc.repository.MissionRepository;
import com.example.umc.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;

    private final ReviewRepository reviewRepository;

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Page<Review> getReviewList(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).get();
        return reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
    }

    @Override
    public Page<MemberMission> getChallengingMissionList(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).get();
        return memberMissionRepository.findByMemberAndStatus(member, MissionStatus.CHALLENGING, PageRequest.of(page, 10));
    }
}
