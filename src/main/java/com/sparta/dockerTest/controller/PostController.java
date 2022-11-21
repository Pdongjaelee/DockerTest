package com.sparta.dockerTest.controller;

import com.sparta.dockerTest.dto.request.PostRequestDto;
import com.sparta.dockerTest.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    //게시글 작성
    @PostMapping("/api/auth/posts")
    public ResponseEntity<?> createPost(@RequestBody PostRequestDto postRequestDto, HttpServletRequest request){
        return postService.createPost(postRequestDto, request);
    }
    //게시글 조회
    @GetMapping("/api/posts")
    public ResponseEntity<?> getAllPosts(){
        return postService.getAllPosts();
    }

    //게시글 아이디로 상세조회
    @GetMapping("/api/posts/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id){
        return postService.getPost(id);
    }

    //게시글 수정
    @PutMapping("/api/auth/posts/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id,
                                     @RequestBody PostRequestDto postRequestDto,
                                                     HttpServletRequest request)
    {
        return postService.updatePost(id, postRequestDto, request);
    }
    //게시글 삭제
    @DeleteMapping(value = "/api/auth/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id, HttpServletRequest request){
        return postService.deletePost(id, request);
    }
}
