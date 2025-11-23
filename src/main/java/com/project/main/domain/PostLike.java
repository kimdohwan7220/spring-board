package com.project.main.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "post_likes")
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private String username;

    protected PostLike() {}

    public PostLike(Long postId, String username) {
        this.postId = postId;
        this.username = username;
    }

    public Long getPostId() { return postId; }
    public String getUsername() { return username; }
}
