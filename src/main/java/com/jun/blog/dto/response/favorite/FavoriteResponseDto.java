package com.jun.blog.dto.response.favorite;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jun.blog.common.ResponseCode;
import com.jun.blog.common.ResponseMessage;
import com.jun.blog.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class FavoriteResponseDto extends ResponseDto{

    private FavoriteResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<FavoriteResponseDto> success() {
        FavoriteResponseDto result = new FavoriteResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistPost() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_POST, ResponseMessage.NOT_EXISTED_POST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
    
}
