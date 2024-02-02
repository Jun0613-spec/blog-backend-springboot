package com.jun.blog.controller;

import org.springframework.web.bind.annotation.RestController;

import com.jun.blog.dto.response.search.GetPopularListResponseDto;
import com.jun.blog.dto.response.search.GetRelationListResponseDto;
import com.jun.blog.service.SearchService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/popular")
    public ResponseEntity<? super GetPopularListResponseDto> getPopularList(){
        ResponseEntity<? super GetPopularListResponseDto> response = searchService.getPopularList();
        return response;
    }

    @GetMapping("/{searchWord}/relation")
    public ResponseEntity<? super GetRelationListResponseDto> getRelationList(
        @PathVariable("searchWord") String searchWord) {
        ResponseEntity<? super GetRelationListResponseDto> response = searchService.getRelationList(searchWord);
        return  response;
    }
    
    
    
}
