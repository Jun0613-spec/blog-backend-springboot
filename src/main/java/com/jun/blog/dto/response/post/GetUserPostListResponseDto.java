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
public class GetUserPostListResponseDto extends ResponseDto{

    private List<PostListItem> userPostList;

    private GetUserPostListResponseDto(List<PostListView> postListViews) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.userPostList = PostListItem.getList(postListViews);
    }

    public static ResponseEntity<GetUserPostListResponseDto> success(List<PostListView> postListViews){
        GetUserPostListResponseDto result = new GetUserPostListResponseDto(postListViews);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    
    public static ResponseEntity<ResponseDto> notExistUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
