package com.jun.blog.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.blog.dto.request.post.PostRequestDto;
import com.jun.blog.dto.request.post.UpdatePostRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="post")
public class Post {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    private String title;
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    private Integer viewCount;
    private Integer favoriteCount;
    private Integer commentCount;
    private String postEmail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Post(PostRequestDto dto, String email, User user) {
        
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.createdAt = LocalDateTime.now();
        this.favoriteCount = 0;
        this.commentCount = 0;
        this.viewCount = 0;
        this.user = user;
        this.postEmail = email;
    }

    public void incrementViewCount() {
        this.viewCount++;
    }

    public void incrementFavoriteCount() {
        this.favoriteCount++;
    }

    public void incrementCommentCount() {
        this.commentCount++;
    }

    public void decrementFavoriteCount() {
        this.favoriteCount--;
    }

    public void decrementCommentCount() {
        this.commentCount--;
    }
    
    public void updatePost(UpdatePostRequestDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }

}
