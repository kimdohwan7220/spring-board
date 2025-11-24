package com.project.main.dto.response;

import com.project.main.domain.Comment;
import java.time.LocalDateTime;

public record CommentResponse(
        Long id,
        String writer,
        String content,
        LocalDateTime createdAt,
        String profileImage
) {
    public static CommentResponse from(Comment c, String profileImage) {
        return new CommentResponse(
                c.getId(),
                c.getWriter(),
                c.getContent(),
                c.getCreatedAt(),
                profileImage
        );
    }
}
