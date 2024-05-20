package com.example.umc.apiPayload.code.status;

import com.example.umc.apiPayload.code.BaseErrorCode;
import com.example.umc.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    // 멤버 관려 에러
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4001", "해당 사용자가 존재하지 않습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),

    // 스토어 관련 에러
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE_4001","해당 가게가 존재하지 않습니다."),

    // 미션 관련 에러
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION_4001","해당 미션은 존재하지 않습니다."),
    MISSION_ALREADY_IN_PROGRESS(HttpStatus.BAD_REQUEST, "MISSION_4002","이미 진행중인 미션입니다."),

    // 음식 카테고리 관련 에러
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD4001", "음식 카테고리에서 찾을 수 없습니다."),

    // 지역 관련 에러
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "REGION_4001","해당 지역이 존재하지 않습니다."),

    // 게시글 관련 에러
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다."),

    // TEST
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
