package com.jun.blog.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jun.blog.common.ResponseCode;
import com.jun.blog.common.ResponseMessage;
import com.jun.blog.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class UpdateUserNameResponseDto extends ResponseDto{

     private UpdateUserNameResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }
   
    public static ResponseEntity<UpdateUserNameResponseDto> success() {
        UpdateUserNameResponseDto result = new UpdateUserNameResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> duplicateUserName() {
        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_USERNAME, ResponseMessage.DUPLICATE_USERNAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
       
}
