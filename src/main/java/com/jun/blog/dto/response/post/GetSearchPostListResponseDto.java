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
public class GetSearchPostListResponseDto extends ResponseDto{
    
    private List<PostListItem> searchList;

    private GetSearchPostListResponseDto(List<PostListView> postListViews) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.searchList = PostListItem.getList(postListViews);
    }

    public static ResponseEntity<GetSearchPostListResponseDto> success(List<PostListView> postListViews) {
        GetSearchPostListResponseDto result = new GetSearchPostListResponseDto(postListViews);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    
}
