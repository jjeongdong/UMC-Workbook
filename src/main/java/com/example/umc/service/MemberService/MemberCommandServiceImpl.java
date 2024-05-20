package com.example.umc.service.MemberService;

import com.example.umc.apiPayload.code.status.ErrorStatus;
import com.example.umc.apiPayload.exception.handler.FoodCategoryHandler;
import com.example.umc.converter.FoodLikesConverter;
import com.example.umc.converter.MemberConverter;
import com.example.umc.domain.FoodCategory;
import com.example.umc.domain.Member;
import com.example.umc.domain.mapping.FoodLikes;
import com.example.umc.repository.FoodCategoryRepository;
import com.example.umc.repository.MemberRepository;
import com.example.umc.web.dto.MemberRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);
        List<FoodCategory> foodCategoryList = request.getFoodLikesList().stream()
                .map(category -> foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND))).collect(Collectors.toList());

        List<FoodLikes> foodLikesList = FoodLikesConverter.toFoodLikesList(foodCategoryList);

        foodLikesList.forEach(foodLikes -> {foodLikes.setMember(newMember);});

        return memberRepository.save(newMember);
    }
}
