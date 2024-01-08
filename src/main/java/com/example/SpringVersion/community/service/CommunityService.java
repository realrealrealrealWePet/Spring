package com.example.SpringVersion.community.service;

import com.example.SpringVersion.community.dto.AddPostRequest;
import com.example.SpringVersion.community.dto.UpdatePostRequest;
import com.example.SpringVersion.community.entity.Post;
import com.example.SpringVersion.community.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommunityService {
    private final CommunityRepository communityRepository;

    public Post save(AddPostRequest request) {
        return communityRepository.save(request.toEntity());
    }

    public List<Post> findAll() {
        return communityRepository.findAll();
    }

    public Post findById(long postId) {
        return communityRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + postId));
    }

    public void delete(long postId) {
        communityRepository.deleteById(postId);
    }

    @Transactional
    public Post update(long postId, UpdatePostRequest request) {
        Post post = communityRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + postId));

        post.update(request.getTitle(), request.getContent());

        return post;
    }
}
