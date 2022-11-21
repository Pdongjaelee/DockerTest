package com.sparta.dockerTest.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private String errorCode;
    private String errorMessage;
    private String errorDetail;


    public static ErrorResponse of(ErrorCode errorCode, String message, String errorDetail) {
        return new ErrorResponse(errorCode.getCode(), message, errorDetail); //밑에 생성자를 반환함
    }
    public ErrorResponse(String errorCode, String message, String errorDetail) {
        this.errorCode = errorCode;
        this.errorMessage = message;
        this.errorDetail = errorDetail;
    }
}