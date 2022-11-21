package com.sparta.dockerTest.service;

import com.sparta.dockerTest.dto.request.CommentRequestDto;
import com.sparta.dockerTest.dto.response.*;
import com.sparta.dockerTest.entity.Comment;
import com.sparta.dockerTest.entity.Member;
import com.sparta.dockerTest.entity.Post;
import com.sparta.dockerTest.exception.CommentException.CommentIdNotFoundException;
import com.sparta.dockerTest.exception.MemberException.PostIdNotFoundException;
import com.sparta.dockerTest.exception.PostException.AccessTokenNotFoundException;
import com.sparta.dockerTest.exception.PostException.ContentNotFoundException;
import com.sparta.dockerTest.exception.PostException.RefreshTokenNotFoundException;
import com.sparta.dockerTest.exception.PostException.TokenInvalidException;
import com.sparta.dockerTest.jwt.provider.JwtProvider;
import com.sparta.dockerTest.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

	private final PostService postService;
	private final CommentRepository commentRepository;
	private final JwtProvider jwtProvider;

	private Member validateMember(HttpServletRequest request) {
		if (!jwtProvider.validateToken(request.getHeader("Refresh-Token"))) {
			return null;
		}
		return jwtProvider.getMemberFromAuthentication();
	}
	//댓글이 있는지 없는지
	@Transactional(readOnly = true)
	public Comment isPresentComment(Long id){
		Optional<Comment> optionalComment = commentRepository.findById(id);
		return optionalComment.orElse(null);
	}

	public ResponseEntity<?> createComment(CommentRequestDto commentRequestDto, HttpServletRequest request) {
		if(null == request.getHeader("Authorization")){
			throw new AccessTokenNotFoundException();
		}
		if(null == request.getHeader("Refresh-Token")){
			throw new RefreshTokenNotFoundException();
		}
		Member member = validateMember(request);
		if(null == member){
			throw new TokenInvalidException();
		}
		if(commentRequestDto.getContent()==null){
			throw new ContentNotFoundException();
		}

		Post post = postService.isPresentPost(commentRequestDto.getPostId());
		if (null == post) {
			throw new PostIdNotFoundException();
		}

		Comment comment = Comment.builder()
				.post(post)
				.content(commentRequestDto.getContent())
				.member(member)
				.build();
		commentRepository.save(comment);
		return ResponseEntity.ok().body(ResponseDto.success(
				CommentResponseDto.builder()
						.id(comment.getId())
						.author(comment.getMember().getUsername())
						.content(comment.getContent())
						.createdAt(comment.getCreatedAt())
						.modifiedAt(comment.getModifiedAt())
						.build())
		);
	}
	//댓글 상세 조회
	@Transactional(readOnly = true)
	public ResponseEntity<?> getComment(Long postId) {
		Comment comment = isPresentComment(postId);
		if(null == comment){
			throw new CommentIdNotFoundException();
		}
		return ResponseEntity.ok().body(ResponseDto.success(
				CommentResponseDto.builder()
						.id(comment.getId())
						.content(comment.getContent())
						.author(comment.getMember().getUsername())
						.createdAt(comment.getCreatedAt())
						.modifiedAt(comment.getModifiedAt())
						.build())
		);
	}
	//댓글 수정
	@Transactional
	public ResponseEntity<?> updateComment(Long id, CommentRequestDto requestDto, HttpServletRequest request) {
			if(null == request.getHeader("Authorization")) {
				throw new AccessTokenNotFoundException();
			}
			if(null == request.getHeader("Refresh-Token")){
				throw new RefreshTokenNotFoundException();
			}
			Member member = validateMember(request);
			if(null == member){
				throw new TokenInvalidException();
			}
			Post post = postService.isPresentPost(requestDto.getPostId());
			if(null == post){
				throw new PostIdNotFoundException();
			}
			Comment comment = isPresentComment(id);
			if(null == comment){
				throw new CommentIdNotFoundException();
			}
//		if(comment.validateMember(member)){
////			return ResponseDto.fail("BAD_REQUEST", "작성자만 수정할 수 있습니다.");
////		}
		comment.update(requestDto);
		commentRepository.save(comment);
		return ResponseEntity.ok().body(ResponseDto.success(
				CommentResponseDto.builder()
						.id(comment.getId())
						.content(comment.getContent())
						.author(comment.getMember().getUsername())
						.createdAt(comment.getCreatedAt())
						.modifiedAt(comment.getModifiedAt())
						.build())
		);
	}
	//댓글 삭제
	@Transactional
	public ResponseEntity<?> deleteComment(Long id, HttpServletRequest request) {
		if(null == request.getHeader("Authorization")){
			throw new RefreshTokenNotFoundException();
		}
		if(null == request.getHeader("Refresh-Token")){
			throw new AccessTokenNotFoundException();
		}
		Member member = validateMember(request);
		if(null == member){
			throw new TokenInvalidException();
		}

		Comment comment = isPresentComment(id);
		if(null == comment){
			throw new CommentIdNotFoundException();
		}
//		if(comment.validateMember(member)){
//			return ResponseDto.fail("BAD_REQUEST", "작성자만 수정할 수 있습니다.");
//		}
		commentRepository.delete(comment);
		return ResponseEntity.ok().body(ResponseDto.success("success"));
	}
}