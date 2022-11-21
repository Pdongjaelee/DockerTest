package com.sparta.dockerTest.exception;

import com.sparta.dockerTest.exception.CommentException.CommentIdNotFoundException;
import com.sparta.dockerTest.exception.MemberException.*;
import com.sparta.dockerTest.exception.PostException.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    //중복 id 확인
    @ExceptionHandler(DuplicateMemberException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateMemberException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.DUPLICATE_MEMBER_ID, ErrorCode.DUPLICATE_MEMBER_ID.getMessage(), ErrorCode.DUPLICATE_MEMBER_ID.getDetail());
        log.error("error: {}, stacktrace: {}", errorResponse, e.getStackTrace());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    // password 일치확인ㅊ
    @ExceptionHandler(passwordConfirmException.class)
    public ResponseEntity<ErrorResponse> handlePasswordConfirmException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.BAD_PASSWORD_CONFIRM, ErrorCode.DUPLICATE_MEMBER_ID.getMessage(), ErrorCode.BAD_PASSWORD_CONFIRM.getDetail());
        log.error("error: {}, stacktrace: {}", errorResponse, e.getStackTrace());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // 회원이 존재하지 않을 때
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerMemberNotFoundException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.MEMBER_NOT_FOUND, ErrorCode.MEMBER_NOT_FOUND.getMessage(), ErrorCode.MEMBER_NOT_FOUND.getDetail());
        log.error("error:{}, stacktrace:{}", errorResponse, e.getStackTrace());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 비밀번호가 맞는지 확인 -> 이건 HttpStatus에 있는 것을 가져다 쓰는 경우
    @ExceptionHandler(BadPasswordException.class)
    public ResponseEntity<ErrorResponse> handleBadPasswordException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.BAD_PASSWORD, ErrorCode.BAD_PASSWORD.getMessage(), ErrorCode.BAD_PASSWORD.getDetail());
        log.error("error:{}, stacktrace:{}", errorResponse, e.getStackTrace());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    // Access 토큰값 있는지 없는지
    @ExceptionHandler(AccessTokenNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAccessTokenNotFoundException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.AUTHORIZATION_IS_EMPTY, ErrorCode.AUTHORIZATION_IS_EMPTY.getMessage(), ErrorCode.AUTHORIZATION_IS_EMPTY.getDetail());
        log.error("error:{}, stacktrace:{}", errorResponse, e.getStackTrace());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
    // Refresh 토큰값 있는지 없는지
    @ExceptionHandler(RefreshTokenNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerRefreshTokenNotFoundException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.REFRESHTOKEN_IS_EMPTY, ErrorCode.REFRESHTOKEN_IS_EMPTY.getMessage(), ErrorCode.REFRESHTOKEN_IS_EMPTY.getDetail());
        log.error("error:{}, stacktrace:{}", errorResponse, e.getStackTrace());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }


    // 토큰 값이 맞는지
    @ExceptionHandler(TokenInvalidException.class)
    public ResponseEntity<ErrorResponse> handleTokenInvalidException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_TOKEN, ErrorCode.INVALID_TOKEN.getMessage(), ErrorCode.INVALID_TOKEN.getDetail());
        log.error("error:{}, stacktrace:{}", errorResponse, e.getStackTrace());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
    //제목 확인
    @ExceptionHandler(TitleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTitleNotFoundException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.TITLE_NOT_FOUND, ErrorCode.TITLE_NOT_FOUND.getMessage(), ErrorCode.TITLE_NOT_FOUND.getDetail());
        log.error("error:{}, stacktrace:{}", errorResponse, e.getStackTrace());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    //내용 확인
    @ExceptionHandler(ContentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleContentNotFoundException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.CONTENT_NOT_FOUND, ErrorCode.CONTENT_NOT_FOUND.getMessage(), ErrorCode.CONTENT_NOT_FOUND.getDetail());
        log.error("error:{}, stacktrace:{}", errorResponse, e.getStackTrace());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

    //게시글 ID 확인
    @ExceptionHandler(PostIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePostIdNotFoundException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.POST_ID_NOT_FOUND, ErrorCode.POST_ID_NOT_FOUND.getMessage(), ErrorCode.POST_ID_NOT_FOUND.getDetail());
        log.error("error:{}, stacktrace:{}", errorResponse, e.getStackTrace());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

        }

    // 댓글 ID 확인
    @ExceptionHandler(CommentIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCommentIdNotFoundException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.COMMENT_ID_NOT_FOUND, ErrorCode.COMMENT_ID_NOT_FOUND.getMessage(), ErrorCode.COMMENT_ID_NOT_FOUND.getDetail());
        log.error("error:{}, stacktrace:{}", errorResponse, e.getStackTrace());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }