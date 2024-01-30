package com.jun.blog.repository.resultSet;

import java.time.LocalDateTime;

public interface GetCommentListResultSet {
    
    Integer getCommentId();
    String getUserName();
    String getProfileImage();
    LocalDateTime getCreatedAt();
    String getContent();
   
}
