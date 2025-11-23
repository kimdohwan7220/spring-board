package com.project.main.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private int likes = 0;

    @Column(nullable = false)
    private int views = 0;

    @Column(nullable = false)
    private int commentCount = 0;

    protected Post() {}

    public Post(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = LocalDateTime.now();
    }

    public void updateContent(String newTitle, String newContent) {
        this.title = newTitle;
        this.content = newContent;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getWriter() { return writer; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public int getLikes() { return likes; }
    public int getViews() { return views; }
    public int getCommentCount() { return commentCount; }

    public void increaseViews() { this.views++; }
    public void increaseCommentCount() { this.commentCount++; }

    public void setLikes(int likes) { this.likes = likes; }
}
