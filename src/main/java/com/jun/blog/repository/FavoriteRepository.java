package com.jun.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jun.blog.model.Favorite;
import com.jun.blog.repository.resultSet.GetFavoriteListResultSet;

import jakarta.transaction.Transactional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer>{
    
    @Query("SELECT f FROM Favorite f WHERE f.post.postId = :postId AND f.user.userId = :userId")
    Favorite findByPostIdAndUserId(Integer postId, Integer userId);
    
    @Query(
        value = 
        "SELECT " +
        "U.user_id AS userId, " +
        "U.email AS email, " +
        "U.user_name AS userName," +
        "U.profile_image AS profileImage " +
        "FROM favorite AS F " +
        "INNER JOIN user AS U " +
        "ON F.user_id = U.user_id " +
        "WHERE F.post_id = ?1",
        nativeQuery = true
    )
    List<GetFavoriteListResultSet> getFavoriteList(Integer postId);
    
   
   @Transactional
   @Modifying
   @Query("DELETE FROM Favorite f WHERE f.post.id = :postId")
   void deleteByPostId(@Param("postId") Integer postId);
}
