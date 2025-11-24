package com.project.main.controller;

import com.project.main.dto.request.CommentCreateRequest;
import com.project.main.dto.request.CommentUpdateRequest;
import com.project.main.dto.response.CommentResponse;
import com.project.main.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @PostMapping
    public CommentResponse create(
            @PathVariable Long postId,
            @RequestBody CommentCreateRequest req
    ) {
        return service.create(postId, req.getWriter(), req.getContent());
    }

    @GetMapping
    public List<CommentResponse> getComments(@PathVariable Long postId) {
        return service.getComments(postId);
    }

    @PutMapping("/{commentId}")
    public CommentResponse update(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequest req
    ) {
        return service.update(postId, commentId, req.getContent());
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long postId,
            @PathVariable Long commentId
    ) {
        service.delete(postId, commentId);
        return ResponseEntity.noContent().build();
    }
}
