package com.example.SpringVersion;

import com.example.SpringVersion.community.dto.AddPostRequest;
import com.example.SpringVersion.community.dto.UpdatePostRequest;
import com.example.SpringVersion.community.entity.Post;
import com.example.SpringVersion.community.repository.CommunityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class CommunityApiControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    CommunityRepository communityRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        communityRepository.deleteAll();
    }

    @DisplayName("addPost 성공~")
    @Test
    public void addPost() throws Exception {
        final String url = "/api/posts";
        final String title = "title";
        final String content = "content";
        final AddPostRequest userRequest = new AddPostRequest(title, content);

        final String requestBody = objectMapper.writeValueAsString(userRequest);

        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        result.andExpect(status().isCreated());

        List<Post> posts = communityRepository.findAll();

        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts.get(0).getTitle()).isEqualTo(title);
        assertThat(posts.get(0).getContent()).isEqualTo(content);
    }

    @DisplayName("findAllPost 성공~")
    @Test
    public void findAllPosts() throws Exception {
        final String url = "/api/posts";
        final String title = "title";
        final String content = "content";

        communityRepository.save(Post.builder()
                .title(title)
                .content(content)
                .build());

        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content))
                .andExpect(jsonPath("$[0].title").value(title))
                ;

    }

    @DisplayName("findPost 성공~")
    @Test
    public void findPost() throws Exception {
        final String url = "/api/posts/{postId}";
        final String title = "title";
        final String content = "content";

        Post savedPost = communityRepository.save(Post.builder()
                .title(title)
                .content(content)
                .build());

        final ResultActions resultActions = mockMvc.perform(get(url, savedPost.getPostId()));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.title").value(title));
    }

    @DisplayName("deletePost 성공~")
    @Test
    public void deletePost() throws Exception {
        final String url = "/api/posts/{postId}";
        final String title = "title";
        final String content = "content";

        Post savedPost = communityRepository.save(Post.builder()
                .title(title)
                .content(content)
                .build());

        mockMvc.perform(delete(url, savedPost.getPostId()))
                .andExpect(status().isOk());

        List<Post> posts = communityRepository.findAll();

        assertThat(posts).isEmpty();
    }

    @DisplayName("updatePost 성공~")
    @Test
    public void updatePost() throws Exception {
        final String url = "/api/posts/{postId}";
        final String title = "title";
        final String content = "content";

        Post savedPost = communityRepository.save(Post.builder()
                .title(title)
                .content(content)
                .build());

        final String newTitle = "new title";
        final String newContent = "new content";

        UpdatePostRequest request = new UpdatePostRequest(newTitle, newContent);

        ResultActions result = mockMvc.perform(put(url, savedPost.getPostId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isOk());

        Post post = communityRepository.findById(savedPost.getPostId()).get();

        assertThat(post.getTitle()).isEqualTo(newTitle);
        assertThat(post.getContent()).isEqualTo(newContent);
    }
}
