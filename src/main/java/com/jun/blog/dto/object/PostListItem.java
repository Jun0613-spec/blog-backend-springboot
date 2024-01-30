package com.jun.blog.dto.object;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.blog.model.PostListView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostListItem {
    
    private Integer postId;
    private String title;
    private String content;
    private String postTitleImage;
    private int favoriteCount;
    private int commentCount;
    private int viewCount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    private String postUserName;
    private String postUserProfileImage;

    public PostListItem(PostListView postListView) {
        this.postId = postListView.getPostId();
        this.title =postListView.getTitle();
        this.content = postListView.getContent();
        this.postTitleImage =postListView.getTitleImage();
        this.favoriteCount =postListView.getFavoriteCount();
        this.commentCount =postListView.getCommentCount();
        this.viewCount =postListView.getViewCount();
        this.createdAt =postListView.getCreatedAt();
        this.postUserName =postListView.getPostUserName();
        this.postUserProfileImage =postListView.getPostUserProfileImage();
    }

    public static List<PostListItem> getList(List<PostListView> postListViews) {
        List<PostListItem> list = new ArrayList<>();
        for(PostListView postListView: postListViews) {
            PostListItem postListItem = new PostListItem(postListView);
            list.add(postListItem);
        }

        return list;
    }
}
