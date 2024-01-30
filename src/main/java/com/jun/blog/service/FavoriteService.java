package com.jun.blog.service;

import org.springframework.http.ResponseEntity;

import com.jun.blog.dto.response.favorite.FavoriteResponseDto;
import com.jun.blog.dto.response.favorite.GetFavoriteListResponseDto;

public interface FavoriteService {

    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer postId);

    ResponseEntity<? super FavoriteResponseDto> favorite(Integer postId, Integer userId);
}
