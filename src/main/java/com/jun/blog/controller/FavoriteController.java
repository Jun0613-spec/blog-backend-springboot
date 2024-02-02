package com.jun.blog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jun.blog.dto.response.favorite.FavoriteResponseDto;
import com.jun.blog.dto.response.favorite.GetFavoriteListResponseDto;
import com.jun.blog.model.User;
import com.jun.blog.repository.UserRepository;
import com.jun.blog.service.FavoriteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://blog-frontend-one-hazel.vercel.app")
public class FavoriteController {

      private final FavoriteService favoriteService;

      private final UserRepository userRepository;

      @GetMapping("/{postId}/favoritelist")
      public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(
        @PathVariable("postId") Integer postId) {

        ResponseEntity<? super  GetFavoriteListResponseDto> response = favoriteService.getFavoriteList(postId);

        return response;
    }

      @PutMapping("/{postId}/favorite")
      public ResponseEntity<? super FavoriteResponseDto> putFavorite(
      @PathVariable("postId") Integer postId, 
      @AuthenticationPrincipal String email) {

        User user = userRepository.findByEmail(email);
        if(user == null) return FavoriteResponseDto.notExistUser();

        ResponseEntity<? super FavoriteResponseDto> response = favoriteService.favorite(postId, user.getUserId());
        return response;
     }

     
    
}
