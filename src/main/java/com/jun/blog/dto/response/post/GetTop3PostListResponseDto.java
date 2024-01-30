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
public class GetTop3PostListResponseDto extends ResponseDto{

    private List<PostListItem> top3List;

    private GetTop3PostListResponseDto(List<PostListView> posts) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.top3List = PostListItem.getList(posts);
    }

    public static ResponseEntity<GetTop3PostListResponseDto> success(List<PostListView> posts) {
        GetTop3PostListResponseDto result = new GetTop3PostListResponseDto(posts);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    
}
