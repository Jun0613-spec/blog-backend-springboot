package com.jun.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jun.blog.model.Post;
import com.jun.blog.repository.resultSet.GetPostResultSet;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    boolean existsByPostId(Integer postId);
    
    //Post findPostByPostId(Integer postId);

    Post findByPostId(Integer postId);

     @Query(
        value=
        "SELECT " + 
        "P.post_id AS postId, " +
        "P.title AS title, " +
        "P.content AS content, " +
        "P.created_at AS createdAt, " +
        "P.post_email AS postEmail, " +
        "P.user_id AS userId, " +
        "U.email AS email, " +
        "U.user_name AS postUserName, " +
        "U.profile_image AS postUserProfileImage " +
        "FROM post AS P " +
        "INNER JOIN user AS U " +        
        "ON P.user_id = U.user_id " +
        "WHERE post_id = ?1 ",
        nativeQuery = true
    )
    GetPostResultSet getPost(Integer postId);
}
