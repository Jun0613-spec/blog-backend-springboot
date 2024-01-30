package com.jun.blog.dto.response.post;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jun.blog.common.ResponseCode;
import com.jun.blog.common.ResponseMessage;
import com.jun.blog.dto.object.PostListItem;
import com.jun.blog.dto.response.ResponseDto;

import com.jun.blog.model.PostListView;

import lombok.Getter;

@Getter
public class GetLatestPostListResponseDto extends ResponseDto{

    private List<PostListItem> latestList;

    private GetLatestPostListResponseDto(List<PostListView> posts) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.latestList = PostListItem.getList(posts);
    }

    public static ResponseEntity<GetLatestPostListResponseDto> success(List<PostListView> posts) {
        GetLatestPostListResponseDto result = new GetLatestPostListResponseDto(posts);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    
}
