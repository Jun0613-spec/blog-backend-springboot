package com.jun.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jun.blog.dto.response.post.GetLatestPostListResponseDto;
import com.jun.blog.dto.response.post.GetSearchPostListResponseDto;
import com.jun.blog.dto.response.post.GetTop3PostListResponseDto;
import com.jun.blog.dto.response.post.GetUserPostListResponseDto;
import com.jun.blog.service.PostListViewService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/post/list")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://blog-frontend-one-hazel.vercel.app")
public class PostListViewController {

    private final PostListViewService postListViewService;

    @GetMapping("/latest")
    public ResponseEntity<? super GetLatestPostListResponseDto> getLatestPostList() {
        ResponseEntity<? super GetLatestPostListResponseDto> response = postListViewService.getLatestPostList();
        return response;
    }

    @GetMapping("/top3")
    public ResponseEntity<? super GetTop3PostListResponseDto> getTop3PostList() {
        ResponseEntity<? super GetTop3PostListResponseDto> response = postListViewService.getTop3PostList();
        return response;
    }

    @GetMapping(value={"/search/{searchWord}", "/search/{searchWord}/{preSearchWord}"})
    public ResponseEntity<? super GetSearchPostListResponseDto> getSearchPostList
    (@PathVariable("searchWord") String searchWord,
     @PathVariable(value="preSearchWord", required=false) String preSearchWord ) {
        ResponseEntity<? super GetSearchPostListResponseDto> response = postListViewService.getSearchPostList(searchWord, preSearchWord);
        return response;
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<? super GetUserPostListResponseDto> getUserPostLIst
    (@PathVariable("userName") String userName
      ) {
        ResponseEntity<? super GetUserPostListResponseDto> response = postListViewService.getUserPostList(userName);
        return response;
    }
    
    
    
}
