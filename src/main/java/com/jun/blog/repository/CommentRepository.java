package com.jun.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jun.blog.model.Comment;
import com.jun.blog.repository.resultSet.GetCommentListResultSet;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{

     Comment findByCommentId(Integer commentId);
    
     @Query(
        value=
        "SELECT " +
        "C.comment_id AS commentId, " +
        "U.email AS email, " +
        "U.user_name AS userName, " +
        "U.profile_image AS profileImage, " +
        "C.created_at AS createdAt, " +
        "C.content AS content " +
        "FROM comment AS C " +
        "INNER JOIN user AS U " +
        "ON C.user_id = U.user_id " +
        "WHERE C.post_id = ?1 " +
        "ORDER BY created_at DESC",
        nativeQuery = true
    )
    List<GetCommentListResultSet> getCommentList(Integer postId);

   @Transactional
   @Modifying
   @Query("DELETE FROM Comment c WHERE c.post.id = :postId")
   void deleteByPostId(@Param("postId") Integer postId);

}
