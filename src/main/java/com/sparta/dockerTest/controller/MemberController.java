package com.sparta.dockerTest.controller;


import com.sparta.dockerTest.dto.request.LoginRequestDto;
import com.sparta.dockerTest.dto.request.MemberRequestDto;
//import com.sparta.post03.dto.response.MemberResponseDto;
//import com.sparta.post03.dto.response.ResponseDto;
import com.sparta.dockerTest.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    //회원가입
    @PostMapping("/api/member/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid MemberRequestDto memberRequestDto){
        return memberService.registerUser(memberRequestDto);
    }

    //로그인
    @PostMapping("/api/member/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse){
        return memberService.login(loginRequestDto, httpServletResponse);

    }
//    @ExceptionHandler({ IllegalArgumentException.class })
//    public ResponseEntity handleException(IllegalArgumentException ex) {
//        RestApiException restApiException = new RestApiException();
//        restApiException.setHttpStatus(HttpStatus.BAD_REQUEST);
//        restApiException.setErrorMessage(ex.getMessage());
//        return new ResponseEntity(
//                // HTTP body
//                restApiException,
//                // HTTP status code
//                HttpStatus.BAD_REQUEST
//        );
//    }
}




