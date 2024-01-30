package com.jun.blog.service;

import org.springframework.http.ResponseEntity;

import com.jun.blog.dto.response.post.GetLatestPostListResponseDto;
import com.jun.blog.dto.response.post.GetSearchPostListResponseDto;
import com.jun.blog.dto.response.post.GetTop3PostListResponseDto;
import com.jun.blog.dto.response.post.GetUserPostListResponseDto;

public interface PostListViewService {

    ResponseEntity<? super GetLatestPostListResponseDto> getLatestPostList();
    ResponseEntity<? super GetTop3PostListResponseDto> getTop3PostList();
    ResponseEntity<? super GetSearchPostListResponseDto> getSearchPostList(String searchWord, String preSearchWord);
    ResponseEntity<? super GetUserPostListResponseDto> getUserPostList(String userName);
}
