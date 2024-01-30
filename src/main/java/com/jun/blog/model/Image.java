package com.jun.blog.model;

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
@Table(name="image")
public class Image {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sequence;
    
    private Integer postId;
    private String image;
    
    public Image(Integer postId, String image) {
        this.postId = postId;
        this.image = image;
    }
    
}
