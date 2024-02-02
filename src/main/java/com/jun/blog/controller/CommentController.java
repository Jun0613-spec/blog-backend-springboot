package com.jun.blog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jun.blog.dto.request.comment.CommentRequestDto;
import com.jun.blog.dto.request.comment.UpdateCommentRequestDto;
import com.jun.blog.dto.response.comment.DeleteCommentResponseDto;
import com.jun.blog.dto.response.comment.GetCommentListResponseDto;
import com.jun.blog.dto.response.comment.PostCommentResponseDto;
import com.jun.blog.dto.response.comment.UpdateCommentResponseDto;
import com.jun.blog.model.Post;
import com.jun.blog.repository.PostRepository;
import com.jun.blog.service.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
@CrossOrigin(origins = "https://blog-frontend-one-hazel.vercel.app")
public class CommentController {

    private final CommentService commentService;

    private final PostRepository postRepository;

    @GetMapping("/{postId}/commentlist")
     public ResponseEntity<? super GetCommentListResponseDto> getCommentList(
        @PathVariable("postId") Integer postId
     ) {
        ResponseEntity<? super GetCommentListResponseDto> response = commentService.getCommentList(postId);

        return response;
     }

     @PostMapping("/{postId}/comment")
     public ResponseEntity<? super PostCommentResponseDto> postComment(
      @RequestBody @Valid CommentRequestDto requestBody,
      @AuthenticationPrincipal String email,
      @PathVariable("postId") Integer postId
     ) {

      Post post = postRepository.findByPostId(postId);

      ResponseEntity<? super PostCommentResponseDto> response = commentService.postComment(requestBody, post, email);

      return response;
     }
     
     @PatchMapping("/{postId}/comment/{commentId}")
     public ResponseEntity<? super UpdateCommentResponseDto> updateComment (
      @RequestBody @Valid UpdateCommentRequestDto requestBody,
      @PathVariable("postId") Integer postId, @PathVariable("commentId") Integer commentId,
      @AuthenticationPrincipal String email
     ) {
      ResponseEntity<? super UpdateCommentResponseDto> response = commentService.updateComment(requestBody,postId,commentId, email);

      return response;
     }

     @DeleteMapping("/{postId}/comment/{commentId}")
     public ResponseEntity<? super DeleteCommentResponseDto> deleteComment (
      @PathVariable("postId") Integer postId,
      @PathVariable("commentId") Integer commentId,
      @AuthenticationPrincipal String email
     ) {
       ResponseEntity<? super DeleteCommentResponseDto> response = commentService.deleteComment(postId, commentId, email);

       return response;
     }
}
