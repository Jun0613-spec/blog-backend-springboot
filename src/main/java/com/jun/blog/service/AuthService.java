package com.jun.blog.service;

import org.springframework.http.ResponseEntity;

import com.jun.blog.dto.request.auth.SignInRequestDto;
import com.jun.blog.dto.request.auth.SignUpRequestDto;
import com.jun.blog.dto.response.auth.SignUpResponseDto;
import com.jun.blog.dto.response.auth.SignInResponseDto;

public interface AuthService {

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
}
