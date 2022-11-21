package com.sparta.dockerTest.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    /* 400 Client Error */
    INVALID_REQUEST("400100000", "Invalid request", "잘못된 요청입니다."),
    INVALID_VALUE("400100001", "Invalid value in the request", "요청에 잘못된 값이 있습니다."),
    AUTHORIZATION_IS_EMPTY("4012001", "Authorization is empty", "Authorization 요청에 헤더가 없습니다."),
    REFRESHTOKEN_IS_EMPTY("4012001", "Refresh Token is empty", "Refresh Token 요청에 헤더가 없습니다."),
    INVALID_TOKEN("4012003", "Token is invalid", "토큰값이 유효하지 않아 인증에 실패했습니다."),
    POST_NOT_FOUND("404", "Post not exist", "존재하지 않는 게시글 입니다."),


    COMMENT_ID_NOT_FOUND("404", "Comment id not exist", "존재 하지 않는 댓글 id 입니다."),
    COMMENT_NOT_FOUND("404", "Comment not found", "존재 하지 않는 댓글 입니다."),
    BAD_PASSWORD_CONFIRM("400", "Password and PasswordConfirm don't match", "비밀번호와 비밀번호 확인이 다릅니다."),
    DUPLICATE_MEMBER_ID("409", "Member is duplicated", "중복된 사용자 ID가 존재합니다."),
    BAD_PASSWORD("401","Password incorrect", "비밀번호를 확인하세요."),
    TITLE_NOT_FOUND("404", "Title is empty", "제목을 작성해 주세요."),
    CONTENT_NOT_FOUND("404", "Content is empty", "내용을 입력해 주세요."),
    POST_ID_NOT_FOUND("404", "post_id not exist", "존재 하지 않는 게시글 ID 입니다."),



    /* 500 Server Error */
    INTERNAL_SERVER_ERROR("500100000", "Internal server error", "서버 오류"),
    DB_EMPTY_DATA_ERROR("D50001", "Empty data from Database", "DB에 데이터가 존재하지 않습니다."),
    MEMBER_NOT_FOUND("5600", "Member not found", "멤버를 찾을 수 없습니다.");

    private final String code;
    private final String message;
    private final String detail;

    ErrorCode(String code, String message, String detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }
}