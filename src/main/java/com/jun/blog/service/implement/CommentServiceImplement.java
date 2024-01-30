package com.jun.blog.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jun.blog.dto.request.comment.CommentRequestDto;
import com.jun.blog.dto.request.comment.UpdateCommentRequestDto;
import com.jun.blog.dto.response.ResponseDto;
import com.jun.blog.dto.response.comment.DeleteCommentResponseDto;
import com.jun.blog.dto.response.comment.GetCommentListResponseDto;
import com.jun.blog.dto.response.comment.PostCommentResponseDto;
import com.jun.blog.dto.response.comment.UpdateCommentResponseDto;
import com.jun.blog.model.Comment;
import com.jun.blog.model.Post;
import com.jun.blog.model.User;
import com.jun.blog.repository.CommentRepository;
import com.jun.blog.repository.PostRepository;
import com.jun.blog.repository.UserRepository;
import com.jun.blog.repository.resultSet.GetCommentListResultSet;
import com.jun.blog.service.CommentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImplement implements CommentService{

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer postId) {

        List<GetCommentListResultSet> resultSets = new ArrayList<>();

        try {

            boolean existedPost = postRepository.existsByPostId(postId);
            if(!existedPost) return GetCommentListResponseDto.notExistPost();

            resultSets = commentRepository.getCommentList(postId);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetCommentListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super PostCommentResponseDto> postComment(CommentRequestDto dto, Post post, String email) {

                try {

                    if(post == null) return PostCommentResponseDto.notExistPost();

                    User user = userRepository.findByEmail(email);

                    boolean existedUser = userRepository.existsByEmail(email);
                    if(!existedUser) return PostCommentResponseDto.notExistUser();

                    Comment comment = new Comment(dto, post, user, email);
                    commentRepository.save(comment);
      
                    post.incrementCommentCount();
                    postRepository.save(post);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResponseDto.databaseError();
                }

                return PostCommentResponseDto.success();
    }

    @Override
    public ResponseEntity<? super UpdateCommentResponseDto> updateComment(UpdateCommentRequestDto dto, Integer postId,Integer commentId,
            String email) {

        try {
            Comment comment = commentRepository.findByCommentId(commentId);
            if(comment == null) return UpdateCommentResponseDto.notExistComment();

            Post post = postRepository.findByPostId(postId);
            if(post == null) return UpdateCommentResponseDto.notExistPost();

            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return UpdateCommentResponseDto.notExistUser();

            String commentUserName = comment.getUserName();
            boolean isPoster = commentUserName.equals(email);
            if(!isPoster) return UpdateCommentResponseDto.noPermission();

            comment.updateComment(dto);
            commentRepository.save(comment);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return UpdateCommentResponseDto.success();
    }

    @Override
    public ResponseEntity<? super DeleteCommentResponseDto> deleteComment(Integer postId, Integer commentId, String email) {
       try {

        Comment comment = commentRepository.findByCommentId(commentId);
            if(comment == null) return UpdateCommentResponseDto.notExistComment();

            Post post = postRepository.findByPostId(postId);
            if(post == null) return UpdateCommentResponseDto.notExistPost();

            String commentUserName = comment.getUserName();
            boolean isPoster = commentUserName.equals(email);
            if(!isPoster) return UpdateCommentResponseDto.noPermission();

            commentRepository.delete(comment);

            post.decrementCommentCount();
            postRepository.save(post);
        
       } catch (Exception e) {
        e.printStackTrace();
        return ResponseDto.databaseError();
       }

       return DeleteCommentResponseDto.success();
    }
    
}
