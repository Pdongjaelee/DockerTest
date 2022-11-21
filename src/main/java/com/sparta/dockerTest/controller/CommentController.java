package com.sparta.dockerTest.controller;

import com.sparta.dockerTest.dto.request.CommentRequestDto;
import com.sparta.dockerTest.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //댓글 작성
    @PostMapping("/api/auth/comment")
    public ResponseEntity<?> createComment(@RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request){
        return commentService.createComment(commentRequestDto, request);
    }

    //댓글 상세 조회
    @GetMapping("api/comment/{id}")
    public ResponseEntity<?> getComment(@PathVariable Long id){
        return commentService.getComment(id);
    }

    //댓글 수정
    @PutMapping("/api/auth/comment/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, HttpServletRequest request){
        return commentService.updateComment(id, requestDto, request);
    }

    //댓글 삭제
    @DeleteMapping("/api/auth/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id, HttpServletRequest request){
        return commentService.deleteComment(id, request);
    }
}
