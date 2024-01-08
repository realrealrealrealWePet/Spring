package com.example.SpringVersion.community.repository;

import com.example.SpringVersion.community.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Post, Long> {
}
