package com.jun.blog.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jun.blog.model.PostListView;

public interface PostListViewRepository extends JpaRepository<PostListView, Integer>{
    
    List<PostListView> findByOrderByCreatedAtDesc();
    List<PostListView> findTop3ByCreatedAtGreaterThanOrderByFavoriteCountDescCommentCountDescViewCountDescCreatedAtDesc(LocalDateTime createdAt);

    List<PostListView> findByTitleContainsOrContentContainsOrderByCreatedAtDesc(String title, String content);

    List<PostListView> findByPostUserNameOrderByCreatedAtDesc(String postUserName);
}
