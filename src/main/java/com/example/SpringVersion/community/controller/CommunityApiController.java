package com.example.SpringVersion.community.controller;

import com.example.SpringVersion.community.dto.AddPostRequest;
import com.example.SpringVersion.community.dto.PostResponse;
import com.example.SpringVersion.community.dto.UpdatePostRequest;
import com.example.SpringVersion.community.entity.Post;
import com.example.SpringVersion.community.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class  CommunityApiController {
    private final CommunityService communityService;

    @PostMapping("/api/posts")
    public ResponseEntity<Post> addPost(@RequestBody AddPostRequest request) {
        Post savedPost = communityService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedPost);
    }

    @GetMapping("/api/posts")
    public ResponseEntity<List<PostResponse>> findAllPosts() {
        List<PostResponse> posts = communityService.findAll()
                .stream()
                .map(PostResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(posts);
    }

    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<PostResponse> findPost(@PathVariable long postId) {
        Post post = communityService.findById(postId);

        return ResponseEntity.ok()
                .body(new PostResponse(post));
    }

    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable long postId) {
        communityService.delete(postId);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/posts/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable long postId, @RequestBody UpdatePostRequest request) {
        Post updatedPost = communityService.update(postId, request);

        return ResponseEntity.ok()
                .body(updatedPost);
    }
}
