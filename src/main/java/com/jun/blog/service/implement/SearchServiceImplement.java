package com.jun.blog.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jun.blog.dto.response.ResponseDto;
import com.jun.blog.dto.response.search.GetPopularListResponseDto;
import com.jun.blog.dto.response.search.GetRelationListResponseDto;
import com.jun.blog.repository.SearchLogRepository;
import com.jun.blog.repository.resultSet.GetPopularListResultSet;
import com.jun.blog.repository.resultSet.GetRelationListResultSet;
import com.jun.blog.service.SearchService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImplement implements SearchService{

    private final SearchLogRepository searchLogRepository;
    
    @Override
    public ResponseEntity<? super GetPopularListResponseDto> getPopularList() {

        List<GetPopularListResultSet> resultSets = new ArrayList<>();
       
        try {

            resultSets = searchLogRepository.getPopularList();
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetPopularListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord) {

       List<GetRelationListResultSet> resultSets = new ArrayList<>();

       try {

        resultSets = searchLogRepository.getRelationList(searchWord);
        
       } catch (Exception e) {
        e.printStackTrace();
        return ResponseDto.databaseError();
       }

       return GetRelationListResponseDto.success(resultSets);
    }
    
}
