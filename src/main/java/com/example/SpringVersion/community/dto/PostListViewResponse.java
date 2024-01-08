package com.example.SpringVersion.community.dto;

import com.example.SpringVersion.community.entity.Post;
import lombok.Getter;

@Getter
public class PostListViewResponse {
    private final Long postId;
    private final String title;
    private final String content;

    public PostListViewResponse(Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
