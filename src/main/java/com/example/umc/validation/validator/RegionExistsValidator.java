package com.example.umc.validation.validator;

import com.example.umc.apiPayload.code.status.ErrorStatus;
import com.example.umc.domain.Member;
import com.example.umc.domain.Region;
import com.example.umc.service.MemberService.MemberQueryService;
import com.example.umc.service.RegionService.RegionQueryService;
import com.example.umc.validation.annotation.ExistMember;
import com.example.umc.validation.annotation.ExistRegion;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RegionExistsValidator implements ConstraintValidator<ExistRegion, Long> {

    private final RegionQueryService regionQueryService;

    @Override
    public void initialize(ExistRegion constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Optional<Region> target = regionQueryService.findRegion(value);

        if (target.isEmpty()){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.REGION_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
