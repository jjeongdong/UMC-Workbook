package com.example.umc.apiPayload.exception.handler;

import com.example.umc.apiPayload.code.BaseErrorCode;
import com.example.umc.apiPayload.exception.GeneralException;

public class ReviewHandler extends GeneralException {

    public ReviewHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
