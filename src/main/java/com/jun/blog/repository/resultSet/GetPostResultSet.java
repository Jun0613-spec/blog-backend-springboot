package com.jun.blog.repository.resultSet;

import java.time.LocalDateTime;

public interface GetPostResultSet {

    Integer getPostId();
    String getTitle();
    String getContent();
    LocalDateTime getCreatedAt();
    Integer getUserId();
    String getPostEmail();
    String getPostUserName();
    String getPostUserProfileImage();
}
