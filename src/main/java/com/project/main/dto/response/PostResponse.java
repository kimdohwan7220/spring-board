package com.project.main.dto.response;
import com.project.main.domain.Post;
import java.time.LocalDateTime;

public record PostResponse(
        Long id,
        String title,
        String content,
        String writer,
        LocalDateTime createdAt,
        int likes,
        int views,
        int commentCount
) {
    public static PostResponse from(Post p) {
        return new PostResponse(
                p.getId(),
                p.getTitle(),
                p.getContent(),
                p.getWriter(),
                p.getCreatedAt(),
                p.getLikes(),
                p.getViews(),
                p.getCommentCount()
        );
    }
}
