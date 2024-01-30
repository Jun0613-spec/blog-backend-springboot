package com.jun.blog.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jun.blog.dto.request.post.PostRequestDto;
import com.jun.blog.dto.request.post.UpdatePostRequestDto;
import com.jun.blog.dto.response.ResponseDto;
import com.jun.blog.dto.response.post.DeletePostResponseDto;
import com.jun.blog.dto.response.post.GetPostResponseDto;
import com.jun.blog.dto.response.post.IncreaseViewCountResponseDto;
import com.jun.blog.dto.response.post.PostResponseDto;
import com.jun.blog.dto.response.post.UpdatePostResponseDto;
import com.jun.blog.model.Image;
import com.jun.blog.model.Post;
import com.jun.blog.model.User;
import com.jun.blog.repository.CommentRepository;
import com.jun.blog.repository.FavoriteRepository;
import com.jun.blog.repository.ImageRepository;
import com.jun.blog.repository.PostRepository;
import com.jun.blog.repository.UserRepository;
import com.jun.blog.repository.resultSet.GetPostResultSet;
import com.jun.blog.service.PostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImplement implements PostService{

    private final PostRepository postRepository;

    private final UserRepository userRepository;
    
    private final ImageRepository imageRepository;

    private final CommentRepository commentRepository;

    private final FavoriteRepository favoriteRepository;

    @Override
    public ResponseEntity<? super GetPostResponseDto> getPostByPostId(Integer postId) {

        GetPostResultSet resultSet = null;
        List<Image> images = new ArrayList<>();

        try {
            resultSet = postRepository.getPost(postId);
            if(resultSet == null) return GetPostResponseDto.notExistPost();

            images = imageRepository.findByPostId(postId);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetPostResponseDto.success(resultSet, images);
    }

    @Override
    public ResponseEntity<? super PostResponseDto> createPost(PostRequestDto dto,String email) {
        
        try {

            boolean existedEmail = userRepository.existsByEmail(email);

            if(!existedEmail) return PostResponseDto.notExistUser();

            User user = userRepository.findByEmail(email);
            
            Post post = new Post(dto, email, user);
            postRepository.save(post);

            Integer postId = post.getPostId();

            List<String> postImageList = dto.getPostImageList();

            List<Image> images = new ArrayList<>();

            for(String image: postImageList) {
                Image imageEntity = new Image(postId, image);
                images.add(imageEntity);
            }

            imageRepository.saveAll(images);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostResponseDto.success();
    }

    @Override
    public ResponseEntity<? super UpdatePostResponseDto> updatePost(UpdatePostRequestDto dto, Integer postId,
            String email) {

        try {

            Post post = postRepository.findByPostId(postId);
            if(post == null) return UpdatePostResponseDto.notExistPost();

            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return UpdatePostResponseDto.notExistUser();

            String postEmail = post.getPostEmail();
            boolean isPoster = postEmail.equals(email);
            if(!isPoster) return UpdatePostResponseDto.noPermission();

            post.updatePost(dto);
            postRepository.save(post);

            imageRepository.deleteByPostId(postId);
            List<String> postImageList = dto.getPostImageList();
            List<Image> images = new ArrayList<>();

            for(String image: postImageList) {
                Image imageEntity = new Image(postId, image);
                images.add(imageEntity);
            }

            imageRepository.saveAll(images);

            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        
        return UpdatePostResponseDto.success();
    }

    @Override
    public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer postId) {

        try {

            Post post = postRepository.findByPostId(postId);
            if(post == null) return IncreaseViewCountResponseDto.notExistPost();
            
            post.incrementViewCount();
            postRepository.save(post);
            
        } catch (Exception e) {
           e.printStackTrace();
           return ResponseDto.databaseError();
        }
        return IncreaseViewCountResponseDto.success();
    }
    
    @Override
    public ResponseEntity<? super DeletePostResponseDto> deletePost(Integer postId, String email) {
        try {
            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return DeletePostResponseDto.notExistUser();

            Post post = postRepository.findByPostId(postId);
            if(post == null) return DeletePostResponseDto.notExistPost();

            String postEmail = post.getPostEmail();
            boolean isPoster = postEmail.equals(email);
            if(!isPoster) return DeletePostResponseDto.noPermission();
            
            imageRepository.deleteByPostId(postId);
            commentRepository.deleteByPostId(postId);
            favoriteRepository.deleteByPostId(postId);

            postRepository.delete(post);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return DeletePostResponseDto.success();
    }

}
