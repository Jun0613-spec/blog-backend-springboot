package com.jun.blog.service;

import org.springframework.http.ResponseEntity;

import com.jun.blog.dto.response.search.GetPopularListResponseDto;
import com.jun.blog.dto.response.search.GetRelationListResponseDto;


public interface SearchService {

    ResponseEntity<? super GetPopularListResponseDto> getPopularList();

    ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord);

    
}
