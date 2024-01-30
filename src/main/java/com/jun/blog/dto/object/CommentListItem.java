package com.jun.blog.dto.object;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.jun.blog.repository.resultSet.GetCommentListResultSet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentListItem {
    
    private Integer commentId;
    private String userName;
    private String profileImage;

    private LocalDateTime createdAt;

    private String content;

    public CommentListItem(GetCommentListResultSet resultSet){
        this.commentId = resultSet.getCommentId();
        this.userName = resultSet.getUserName();
        this.profileImage = resultSet.getProfileImage();
        this.createdAt = resultSet.getCreatedAt();
        this.content = resultSet.getContent();
    }

    public static List<CommentListItem> copyList(List<GetCommentListResultSet> resultSets) {
        List<CommentListItem> list = new ArrayList<>();
        for(GetCommentListResultSet resultSet: resultSets) {
            CommentListItem commentListItem = new CommentListItem(resultSet);
            list.add(commentListItem);
        }
        return list;
    }
    
    
}
