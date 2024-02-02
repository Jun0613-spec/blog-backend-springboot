package com.jun.blog.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Immutable
@Subselect("SELECT " +
"P.post_id AS post_id, " +
"P.title AS title, " +
" P.content AS content, " +
"I.image AS title_image, " +
"P.view_count AS view_count, " +
"P.favorite_count AS favorite_count, " + 
"P.comment_count AS comment_count, " +
"P.created_at AS created_at, " +
"p.user_id AS user_id, " +
"U.email AS post_email, " +
"U.user_name AS post_user_name, " +
"U.profile_image AS post_user_profile_image " +
"FROM post AS P " +
"Inner JOIN user AS U "+
"ON P.user_id = U.user_id " +
"LEFT JOIN (SELECT post_id, ANY_VALUE(image) AS image FROM image GROUP BY post_id) AS I " +
"ON P.post_id = I.post_id") 
public class PostListView {

    @Id
    private int postId;

    private String title;
    private String content;
    private String titleImage;
    private int viewCount;
    private int favoriteCount;
    private int commentCount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    
    private String postEmail;
    private String postUserName;
    private String postUserProfileImage;
   
}
