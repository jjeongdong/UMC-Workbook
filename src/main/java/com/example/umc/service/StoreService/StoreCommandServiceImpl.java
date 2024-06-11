package com.example.umc.service.StoreService;

import com.example.umc.apiPayload.code.status.ErrorStatus;
import com.example.umc.apiPayload.exception.handler.ReviewHandler;
import com.example.umc.aws.s3.AmazonS3Manager;
import com.example.umc.config.AmazonConfig;
import com.example.umc.converter.ReviewConverter;
import com.example.umc.converter.StoreConverter;
import com.example.umc.domain.*;
import com.example.umc.domain.mapping.MemberMission;
import com.example.umc.repository.*;
import com.example.umc.web.dto.StoreRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final ReviewRepository reviewRepository;

    private final MemberRepository memberRepository;

    private final StoreRepository storeRepository;

    private final MissionRepository missionRepository;

    private final MemberMissionRepository memberMissionRepository;

    private final RegionRepository regionRepository;

    private final AmazonS3Manager s3Manager;

    private final UuidRepository uuidRepository;

    private final ReviewImgRepository reviewImgRepository;

    private final AmazonConfig amazonConfig;


    @Override
    public Store createStore(Long regionId, StoreRequestDTO.StoreCreateRequestDTO request) {

        Store store = StoreConverter.toStore(request);

        store.setRegion(regionRepository.findById(regionId).get());

        return storeRepository.save(store);
    }

    @Override
    public Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewRequestDTO request, List<MultipartFile> multipartFiles) throws IOException {

        Member member = memberRepository.findById(memberId).get();
        Store store = storeRepository.findById(storeId).get();

        if (reviewRepository.existsByMemberAndStore(member, store)) {
            throw new ReviewHandler(ErrorStatus.ALREADY_EXISTS_REVIEW);
        }

        Review review = StoreConverter.toReview(request);

        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder().uuid(uuid).build());

        List<String> pictureUrls = s3Manager.upload(multipartFiles, amazonConfig.getReviewPath(), savedUuid);

        review.setMember(member);
        review.setStore(store);

        Review savedReview = reviewRepository.save(review);

        List<ReviewImg> reviewImgList = ReviewConverter.toReviewImgList(pictureUrls, savedReview);
        reviewImgRepository.saveAll(reviewImgList);

        return savedReview;
    }

    @Override
    public Mission createMission(Long storeId, StoreRequestDTO.MissionRequestDTO request) {

        Mission mission = StoreConverter.toMission(request);

        mission.setStore(storeRepository.findById(storeId).get());

        return missionRepository.save(mission);
    }

    @Override
    public MemberMission challengeMission(Long memberId, Long missionId) {

        Member member = memberRepository.findById(memberId).get();
        Mission mission = missionRepository.findById(missionId).get();

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .build();

        return memberMissionRepository.save(memberMission);
    }
}
