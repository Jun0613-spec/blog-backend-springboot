package com.jun.blog.dto.response.post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.blog.common.ResponseCode;
import com.jun.blog.common.ResponseMessage;
import com.jun.blog.dto.response.ResponseDto;
import com.jun.blog.model.Image;
import com.jun.blog.repository.resultSet.GetPostResultSet;

import lombok.Getter;

@Getter
public class GetPostResponseDto extends ResponseDto{

    private Integer postId;
    private String title;
    private String content;
    private List<String> postImageList;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    private Integer userId;
    private String postEmail;
    private String postUserName;
    private String postUserProfileImage;

    private GetPostResponseDto(GetPostResultSet resultSet, List<Image> images) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        List<String> postImageList = new ArrayList<>();
        for(Image image: images) {
            String postImage = image.getImage();
            postImageList.add(postImage);
        }

        this.postId = resultSet.getPostId();
        this.title = resultSet.getTitle();
        this.content = resultSet.getContent();
        this.postImageList = postImageList;
        this.createdAt = resultSet.getCreatedAt();
        this.userId = resultSet.getUserId();
        this.postEmail = resultSet.getPostEmail();
        this.postUserName = resultSet.getPostUserName();
        this.postUserProfileImage = resultSet.getPostUserProfileImage();
    }

    public static ResponseEntity<GetPostResponseDto> success(GetPostResultSet resultSet, List<Image> images) {
        GetPostResponseDto result = new GetPostResponseDto(resultSet,images);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistPost() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_POST, ResponseMessage.NOT_EXISTED_POST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    
}
