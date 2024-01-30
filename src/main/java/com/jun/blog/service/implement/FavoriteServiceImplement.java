package com.jun.blog.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jun.blog.dto.response.ResponseDto;
import com.jun.blog.dto.response.favorite.FavoriteResponseDto;
import com.jun.blog.dto.response.favorite.GetFavoriteListResponseDto;
import com.jun.blog.model.Favorite;
import com.jun.blog.model.Post;
import com.jun.blog.model.User;
import com.jun.blog.repository.FavoriteRepository;
import com.jun.blog.repository.PostRepository;
import com.jun.blog.repository.UserRepository;
import com.jun.blog.repository.resultSet.GetFavoriteListResultSet;
import com.jun.blog.service.FavoriteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImplement implements FavoriteService{

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final FavoriteRepository favoriteRepository;

    @Override
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer postId) {

        List<GetFavoriteListResultSet> resultSets = new ArrayList<>();
        
        try {

            boolean existedPost = postRepository.existsByPostId(postId);
            if(!existedPost) return GetFavoriteListResponseDto.notExistPost();

            resultSets = favoriteRepository.getFavoriteList(postId);
            
        } catch (Exception e) {
           e.printStackTrace();
           return ResponseDto.databaseError();
        }

        return GetFavoriteListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super FavoriteResponseDto> favorite(Integer postId, Integer userId) {

        try {

            User user = userRepository.findByUserId(userId);
            if(user == null) return FavoriteResponseDto.notExistUser();
    
            Post post = postRepository.findByPostId(postId);
            if(post == null) return FavoriteResponseDto.notExistPost();
    
            Favorite favorite = favoriteRepository.findByPostIdAndUserId(postId, userId);
            if(favorite == null) {

            favorite = new Favorite(post, user);
            favoriteRepository.save(favorite);
            post.incrementFavoriteCount();
        }
            else {
                favoriteRepository.delete(favorite);
                post.decrementFavoriteCount();
            }
    
            postRepository.save(post);
    
           } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
           }
    
           return FavoriteResponseDto.success();
    }
    
}
