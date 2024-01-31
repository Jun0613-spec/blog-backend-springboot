package com.jun.blog.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jun.blog.dto.request.user.UpdateProfileImageRequestDto;
import com.jun.blog.dto.request.user.UpdateUserNameRequestDto;
import com.jun.blog.dto.response.ResponseDto;
import com.jun.blog.model.User;
import com.jun.blog.repository.UserRepository;
import com.jun.blog.service.UserService;
import com.jun.blog.dto.response.user.GetSignInUserResponseDto;
import com.jun.blog.dto.response.user.GetUserResponseDto;
import com.jun.blog.dto.response.user.UpdateProfileImageResponseDto;
import com.jun.blog.dto.response.user.UpdateUserNameResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService{

    private final UserRepository userRepository;
    
    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email) {

        User user = null;
        
        try {
           
            user = userRepository.findByEmail(email);
            if(user == null)  return GetSignInUserResponseDto.notExistUser();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();

        }

        return GetSignInUserResponseDto.success(user);
    }

    @Override
    public ResponseEntity<? super GetUserResponseDto> getUser(String userName) {

        User user = null;

        try {

            user = userRepository.findByUserName(userName);
            if(user == null) return GetUserResponseDto.notExistUser();
           
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserResponseDto.success(user);
    }

    @Override
    public ResponseEntity<? super UpdateUserNameResponseDto> updateUserName(UpdateUserNameRequestDto dto, String email) {

       try {

        User user = userRepository.findByEmail(email);
        if(user == null) 
          return UpdateUserNameResponseDto.notExistUser();
        

        String userName= dto.getUserName();
        boolean existedUserName = userRepository.existsByUserName(userName);
        if(existedUserName) return UpdateUserNameResponseDto.duplicateUserName();
        
        user.setUserName(userName);
        userRepository.save(user);
        
       } catch (Exception e) {
        e.printStackTrace();
        return ResponseDto.databaseError();
       }

       return UpdateUserNameResponseDto.success();
    }

    @Override
    public ResponseEntity<? super UpdateProfileImageResponseDto> updateProfileImage(UpdateProfileImageRequestDto dto, String email) {

        try {

            User user = userRepository.findByEmail(email);
            if(user == null) return UpdateProfileImageResponseDto.notExistUser();

            String newProfileImage = dto.getProfileImage();
            user.setProfileImage(newProfileImage);
            userRepository.save(user);
        
        } catch (Exception e) {
         e.printStackTrace();
         return ResponseDto.databaseError();
        }
 
        return UpdateProfileImageResponseDto.success();
       
    }
}
