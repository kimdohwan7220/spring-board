package com.project.main.controller;

import com.project.main.domain.Post;
import com.project.main.dto.request.PostCreateRequest;
import com.project.main.dto.request.PostUpdateRequest;
import com.project.main.dto.response.PostResponse;
import com.project.main.exception.ResourceNotFoundException;
import com.project.main.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @PostMapping
    public PostResponse create(@RequestBody @Valid PostCreateRequest req) {
        Post p = service.create(req.getTitle(), req.getContent(), req.getWriter());
        return PostResponse.from(p);
    }

    @GetMapping
    public List<PostResponse> getAll() {
        return service.getAll().stream()
                .map(PostResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    public PostResponse getOne(@PathVariable Long id) {
        Post p = service.getById(id);
        if (p == null) throw new ResourceNotFoundException("post not found: " + id);
        return PostResponse.from(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody @Valid PostUpdateRequest req) {

        boolean ok = service.update(id, req.getTitle(), req.getContent());

        if (!ok) {
            throw new ResourceNotFoundException("post not found: " + id);
        }

        return ResponseEntity.noContent().build();  // 204 응답
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        boolean ok = service.delete(id);

        if (!ok) {
            throw new ResourceNotFoundException("post not found: " + id);
        }

        return ResponseEntity.noContent().build();  // 204 No Content
    }
}
