package com.jun.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jun.blog.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer>{

    List<Image> findByPostId(Integer postId);
    
    @Transactional
    void deleteByPostId(Integer postId);
}
