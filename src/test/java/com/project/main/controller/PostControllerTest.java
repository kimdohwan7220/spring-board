package com.project.main.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.main.domain.Post;
import com.project.main.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    void testCreatePost() throws Exception {
        // given
        String json = """
        {
          "title": "Hello",
          "content": "World",
          "writer": "Yuna"
        }
        """;

        // when & then
        mockMvc.perform(
                        post("/posts")
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Hello"))
                .andExpect(jsonPath("$.content").value("World"));

        // DB 검증
        assertThat(postRepository.count()).isEqualTo(1);
    }

    @Test
    void testGetOne() throws Exception {
        // given
        Post post = postRepository.save(new Post( "A", "B", "C"));

        // when & then
        mockMvc.perform(get("/posts/" + post.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("A"));
    }

    @Test
    void testUpdatePost() throws Exception {
        // given
        Post post = postRepository.save(new Post("Old", "OldContent", "Yuna"));

        String json = """
        {
          "title": "Updated",
          "content": "UpdatedContent"
        }
        """;

        // when & then
        mockMvc.perform(
                        put("/posts/" + post.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isNoContent());

        // DB 검증
        Post updated = postRepository.findById(post.getId()).orElseThrow();
        assertThat(updated.getTitle()).isEqualTo("Updated");
    }

    @Test
    void testDeletePost() throws Exception {
        // given
        Post post = postRepository.save(new Post("title", "content", "writer"));

        // when & then
        mockMvc.perform(delete("/posts/" + post.getId()))
                .andExpect(status().isNoContent());

        assertThat(postRepository.findById(post.getId())).isEmpty();
    }

    @Test
    void testNotFound() throws Exception {
        mockMvc.perform(get("/posts/999"))
                .andExpect(status().isNotFound());
    }
}
