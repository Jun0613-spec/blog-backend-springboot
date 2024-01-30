package com.jun.blog.service;

import org.springframework.http.ResponseEntity;

import com.jun.blog.dto.request.post.PostRequestDto;
import com.jun.blog.dto.request.post.UpdatePostRequestDto;
import com.jun.blog.dto.response.post.DeletePostResponseDto;
import com.jun.blog.dto.response.post.GetPostResponseDto;
import com.jun.blog.dto.response.post.IncreaseViewCountResponseDto;
import com.jun.blog.dto.response.post.PostResponseDto;
import com.jun.blog.dto.response.post.UpdatePostResponseDto;

public interface PostService {

    ResponseEntity<? super GetPostResponseDto> getPostByPostId(Integer postId);
    
    ResponseEntity<? super PostResponseDto> createPost(PostRequestDto dto,String email);

    ResponseEntity<? super UpdatePostResponseDto> updatePost(UpdatePostRequestDto dto, Integer postId, String email);

    ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer postId);

    ResponseEntity<? super DeletePostResponseDto> deletePost(Integer postId, String email);
}
