package com.project.main.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")   // FK
    private Post post;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    protected Comment() {}

    public Comment(Post post, String writer, String content) {
        this.post = post;
        this.writer = writer;
        this.content = content;
    }

    public Long getId() { return id; }
    public Post getPost() { return post; }
    public String getWriter() { return writer; }
    public String getContent() { return content; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void updateContent(String newContent) {
        this.content = newContent;
    }
}
