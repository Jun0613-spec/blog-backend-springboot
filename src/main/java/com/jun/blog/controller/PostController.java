package com.jun.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jun.blog.dto.request.post.PostRequestDto;
import com.jun.blog.dto.request.post.UpdatePostRequestDto;
import com.jun.blog.dto.response.post.DeletePostResponseDto;
import com.jun.blog.dto.response.post.GetPostResponseDto;
import com.jun.blog.dto.response.post.IncreaseViewCountResponseDto;
import com.jun.blog.dto.response.post.PostResponseDto;
import com.jun.blog.dto.response.post.UpdatePostResponseDto;
import com.jun.blog.service.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://blog-frontend-one-hazel.vercel.app")
public class PostController {

    private final PostService postService;
    
    @GetMapping("/{postId}")
    public ResponseEntity<? super GetPostResponseDto> getPostByPostId(
        @PathVariable("postId") Integer postId) {

        ResponseEntity<? super GetPostResponseDto> response = postService.getPostByPostId(postId);

        return response;
    }

    @GetMapping("/{postId}/views")
     public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount (
        @PathVariable("postId") Integer postId
     ) {
       ResponseEntity<? super IncreaseViewCountResponseDto> response = postService.increaseViewCount(postId);
       return response;
     }

     @PostMapping("")
     public ResponseEntity<? super PostResponseDto> createPost(
        @RequestBody @Valid PostRequestDto requestBody,
        @AuthenticationPrincipal String email) {

        ResponseEntity<? super PostResponseDto> response = postService.createPost(requestBody,email);

        return response;
     }

     @PatchMapping("/{postId}")
     public ResponseEntity<? super UpdatePostResponseDto> updatePost (
      @RequestBody @Valid UpdatePostRequestDto requestBody,
      @PathVariable("postId") Integer postId,
      @AuthenticationPrincipal String email
     ) {
      ResponseEntity<? super UpdatePostResponseDto> response = postService.updatePost(requestBody,postId, email);

      return response;
     }
     
     @DeleteMapping("/{postId}")
     public ResponseEntity<? super DeletePostResponseDto> deletePost(
      @PathVariable("postId") Integer postId,
      @AuthenticationPrincipal String email
     ) {
      ResponseEntity<? super DeletePostResponseDto> response = postService.deletePost(postId, email);
      return response;
     }
    
   
}
