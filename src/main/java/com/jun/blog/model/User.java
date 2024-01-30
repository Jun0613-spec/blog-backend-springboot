package com.jun.blog.model;

import com.jun.blog.dto.request.auth.SignUpRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId; 

    private String email;
    private String password;

    private String userName;
    private String profileImage;

    private boolean agreedPersonal;
    
    public User(SignUpRequestDto dto) {
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.userName = dto.getUserName();
        this.agreedPersonal = dto.getAgreedPersonal();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
    
}
