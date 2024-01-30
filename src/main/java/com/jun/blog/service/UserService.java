package com.jun.blog.service;

import org.springframework.http.ResponseEntity;

import com.jun.blog.dto.request.user.UpdateProfileImageRequestDto;
import com.jun.blog.dto.request.user.UpdateUserNameRequestDto;
import com.jun.blog.dto.response.user.GetSignInUserResponseDto;
import com.jun.blog.dto.response.user.GetUserResponseDto;
import com.jun.blog.dto.response.user.UpdateProfileImageResponseDto;
import com.jun.blog.dto.response.user.UpdateUserNameResponseDto;

public interface UserService {
    
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);

    ResponseEntity<? super GetUserResponseDto> getUser(String userName); 

    ResponseEntity<? super UpdateUserNameResponseDto> updateUserName(UpdateUserNameRequestDto dto, String email);

    ResponseEntity<? super UpdateProfileImageResponseDto> updateProfileImage(UpdateProfileImageRequestDto dto, String email);
}
