package com.jun.blog.dto.request.post;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdatePostRequestDto {
    
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private List<String> postImageList;
}
