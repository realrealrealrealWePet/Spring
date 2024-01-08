package com.example.SpringVersion.community.dto;

import com.example.SpringVersion.community.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class PostViewResponse {
    private Long postId;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public PostViewResponse(Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
    }
}
