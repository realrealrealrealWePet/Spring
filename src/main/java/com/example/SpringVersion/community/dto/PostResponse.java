package com.example.SpringVersion.community.dto;

import com.example.SpringVersion.community.entity.Post;
import lombok.Getter;

@Getter
public class PostResponse {
    private final String title;
    private final String content;

    public PostResponse(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
