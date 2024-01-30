package com.jun.blog.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jun.blog.dto.request.auth.SignInRequestDto;
import com.jun.blog.dto.request.auth.SignUpRequestDto;
import com.jun.blog.dto.response.ResponseDto;
import com.jun.blog.dto.response.auth.SignInResponseDto;
import com.jun.blog.dto.response.auth.SignUpResponseDto;
import com.jun.blog.jwt.JwtProvider;
import com.jun.blog.model.User;
import com.jun.blog.repository.UserRepository;
import com.jun.blog.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService{

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {

        try {

            String email = dto.getEmail();
            boolean existedEmail = userRepository.existsByEmail(email);
            if(existedEmail) return SignUpResponseDto.duplicateEmail();
 
            String userName= dto.getUserName();
            boolean existedUserName = userRepository.existsByUserName(userName);
            if(existedUserName) return SignUpResponseDto.duplicateUserName();
            
            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);
            
            User userEntity = new User(dto);
            userRepository.save(userEntity);
 
         } catch (Exception exception) {
             exception.printStackTrace();
             return ResponseDto.databaseError();
         }

        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        
        String token = null;

        try { 

            String email = dto.getEmail();
            User userEntity = userRepository.findByEmail(email);
            if(userEntity == null) return SignInResponseDto.signInFail();
            
            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if(!isMatched) return SignInResponseDto.signInFail();

            token = jwtProvider.create(email);
            
        } catch (Exception exception) {
           exception.printStackTrace();
           return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);
        
    }
    
}
