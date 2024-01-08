package com.example.SpringVersion.community.controller;

import com.example.SpringVersion.community.dto.PostListViewResponse;
import com.example.SpringVersion.community.dto.PostViewResponse;
import com.example.SpringVersion.community.entity.Post;
import com.example.SpringVersion.community.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CommunityViewController {
    private final CommunityService communityService;

    @GetMapping("/posts")
    public String getPosts(Model model) {
        List<PostListViewResponse> posts = communityService.findAll().stream()
                .map(PostListViewResponse::new)
                .toList();

        model.addAttribute("posts", posts);

        return "postList";
    }

    @GetMapping("/posts/{postId}")
    public String getPost(@PathVariable Long postId, Model model) {
        Post post = communityService.findById(postId);
        model.addAttribute("post", new PostViewResponse(post));

        return "post";
    }

    @GetMapping("/new-post")
    public String newPost(@RequestParam(required = false) Long postId, Model model) {
        if(postId == null) {
            model.addAttribute("post", new PostViewResponse());
        } else {
            Post post = communityService.findById(postId);
            model.addAttribute("post", new PostViewResponse(post));
        }

        return "newPost";
    }
}
