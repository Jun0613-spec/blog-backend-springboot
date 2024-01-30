package com.jun.blog.service.implement;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jun.blog.dto.response.ResponseDto;
import com.jun.blog.dto.response.post.GetLatestPostListResponseDto;
import com.jun.blog.dto.response.post.GetSearchPostListResponseDto;
import com.jun.blog.dto.response.post.GetTop3PostListResponseDto;
import com.jun.blog.dto.response.post.GetUserPostListResponseDto;
import com.jun.blog.model.PostListView;
import com.jun.blog.model.SearchLog;
import com.jun.blog.repository.PostListViewRepository;
import com.jun.blog.repository.SearchLogRepository;
import com.jun.blog.repository.UserRepository;
import com.jun.blog.service.PostListViewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostListViewServiceImplement implements PostListViewService{

    private final PostListViewRepository postListViewRepository;
    private final SearchLogRepository searchLogRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetLatestPostListResponseDto> getLatestPostList() {

        List<PostListView> postListViews = new ArrayList<>(); 

        try {

            postListViews = postListViewRepository.findByOrderByCreatedAtDesc();
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetLatestPostListResponseDto.success(postListViews);
    }

    @Override
    public ResponseEntity<? super GetTop3PostListResponseDto> getTop3PostList() {

       List<PostListView>  postListViews = new ArrayList<>();

       try {

        Date week = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));
        LocalDateTime sevenDaysAgo = week.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        postListViews = postListViewRepository.findTop3ByCreatedAtGreaterThanOrderByFavoriteCountDescCommentCountDescViewCountDescCreatedAtDesc(sevenDaysAgo);
        
       } catch (Exception e) {
        e.printStackTrace();
        return ResponseDto.databaseError();
       }

       return GetTop3PostListResponseDto.success(postListViews);
    }

    @Override
    public ResponseEntity<? super GetSearchPostListResponseDto> getSearchPostList(String searchWord,
            String preSearchWord) {

        List<PostListView> postListViews = new ArrayList<>();

        try {

            postListViews = postListViewRepository.findByTitleContainsOrContentContainsOrderByCreatedAtDesc(searchWord, searchWord);

            SearchLog searchLog = new SearchLog(searchWord, preSearchWord, false);
            searchLogRepository.save(searchLog);

            boolean relation = preSearchWord != null;
            if(relation) {
                searchLog = new SearchLog(searchWord, preSearchWord, relation);
                searchLogRepository.save(searchLog);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSearchPostListResponseDto.success(postListViews);
    }

    @Override
    public ResponseEntity<? super GetUserPostListResponseDto> getUserPostList(String userName) {
        
        List<PostListView> postListViews = new ArrayList<>();

        try {

            boolean existedUser = userRepository.existsByUserName(userName);
            if(!existedUser) return GetUserPostListResponseDto.notExistUser();

            postListViews = postListViewRepository.findByPostUserNameOrderByCreatedAtDesc(userName);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserPostListResponseDto.success(postListViews);
    }

    
    
}
