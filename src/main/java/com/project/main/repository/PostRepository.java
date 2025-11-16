package com.project.main.repository;

import com.project.main.domain.Post;
import java.util.ArrayList;
import java.util.List;

public class PostRepository {

    private final List<Post> posts = new ArrayList<>();
    private long nextId = 1L;

    public Post save(String title, String content, String writer) {
        Post post = new Post(nextId, title, content, writer);
        posts.add(post);
        nextId++;
        return post;
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts);
    }

    public Post findById(Long id) {
        for (Post post : posts) {
            if (post.hasId(id)) {
                return post;
            }
        }
        return null;
    }

    public boolean update(Long id, String newTitle, String newContent) {
        Post target = findById(id);
        if (target == null) {
            return false;
        }

        target.updateContent(newTitle, newContent);
        return true;
    }

    public boolean delete(Long id) {
        Post target = findById(id);
        if (target == null) {
            return false;
        }

        return posts.remove(target);
    }
}
