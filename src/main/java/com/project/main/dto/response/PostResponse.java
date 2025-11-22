package com.project.main.dto.response;
import com.project.main.domain.Post;

public record PostResponse(Long id, String title, String content, String writer) {
    public static PostResponse from(Post p) {
        return new PostResponse(p.getId(), p.getTitle(), p.getContent(), p.getWriter());
    }
}