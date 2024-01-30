package com.jun.blog.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jun.blog.common.ResponseCode;
import com.jun.blog.common.ResponseMessage;
import com.jun.blog.dto.response.ResponseDto;
import com.jun.blog.model.User;

import lombok.Getter;

@Getter
public class GetUserResponseDto extends ResponseDto{

    private String email;
    private String userName;
    private String profileImage;

    private GetUserResponseDto(User user) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.email = user.getEmail();
        this.userName = user.getUserName();
        this.profileImage = user.getProfileImage();
    }
   
    public static ResponseEntity<GetUserResponseDto> success(User user) {
        GetUserResponseDto result = new GetUserResponseDto(user);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    
}
