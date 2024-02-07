package com.jun.blog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jun.blog.dto.request.user.UpdateProfileImageRequestDto;
import com.jun.blog.dto.request.user.UpdateUserNameRequestDto;
import com.jun.blog.dto.response.user.GetSignInUserResponseDto;
import com.jun.blog.dto.response.user.GetUserResponseDto;
import com.jun.blog.dto.response.user.UpdateProfileImageResponseDto;
import com.jun.blog.dto.response.user.UpdateUserNameResponseDto;
import com.jun.blog.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(
        @AuthenticationPrincipal String email
        ) {

        ResponseEntity<? super GetSignInUserResponseDto> response = userService.getSignInUser(email);
        return response;

    }
    
    @GetMapping("/{userName}")
    public ResponseEntity<? super GetUserResponseDto> getUser(
        @PathVariable("userName") String userName
        ) {

        ResponseEntity<? super GetUserResponseDto> response = userService.getUser(userName);
        return response;

    }

    @PatchMapping("/update/username")
    public ResponseEntity<? super UpdateUserNameResponseDto> updateUserName(
        @RequestBody @Valid UpdateUserNameRequestDto requestBody,
       @AuthenticationPrincipal String email
        ){
       
        ResponseEntity<? super UpdateUserNameResponseDto> response = userService.updateUserName(requestBody, email);

        return response;
    }

    @PatchMapping("/update/profile-image")
    public ResponseEntity<? super UpdateProfileImageResponseDto> updateProfileImage(
        @RequestBody @Valid UpdateProfileImageRequestDto requestBody,
       @AuthenticationPrincipal String email
        ){
       
        ResponseEntity<? super UpdateProfileImageResponseDto> response = userService.updateProfileImage(requestBody, email);

        return response;
    }
}
