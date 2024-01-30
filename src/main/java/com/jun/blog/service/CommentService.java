package com.jun.blog.service;

import org.springframework.http.ResponseEntity;

import com.jun.blog.dto.request.comment.CommentRequestDto;
import com.jun.blog.dto.request.comment.UpdateCommentRequestDto;
import com.jun.blog.dto.response.comment.DeleteCommentResponseDto;
import com.jun.blog.dto.response.comment.GetCommentListResponseDto;
import com.jun.blog.dto.response.comment.PostCommentResponseDto;
import com.jun.blog.dto.response.comment.UpdateCommentResponseDto;
import com.jun.blog.model.Post;

public interface CommentService {

    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer postId);

    ResponseEntity<? super PostCommentResponseDto> postComment(CommentRequestDto dto, Post post, String userName);

    ResponseEntity<? super UpdateCommentResponseDto> updateComment(UpdateCommentRequestDto dto, Integer postId, Integer commentId, String email);

    ResponseEntity<? super DeleteCommentResponseDto> deleteComment(Integer postId,Integer commentId, String email);
    
}
