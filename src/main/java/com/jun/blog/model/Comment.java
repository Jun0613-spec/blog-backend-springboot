package com.jun.blog.model;

import java.time.LocalDateTime;

import com.jun.blog.dto.request.comment.CommentRequestDto;
import com.jun.blog.dto.request.comment.UpdateCommentRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="comment")
@Entity
public class Comment {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    private String content;

    private LocalDateTime createdAt;
    private String userName;
    

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(CommentRequestDto dto, Post post, User user,String userName) {
        
        this.content = dto.getContent();
        this.createdAt = LocalDateTime.now();
        this.user = user;
        this.post = post;
        this.userName = userName;
        
    }

     public void updateComment(UpdateCommentRequestDto dto) {
        this.content = dto.getContent();
    }
}
