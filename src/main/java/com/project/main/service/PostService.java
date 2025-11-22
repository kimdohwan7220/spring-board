package com.project.main.service;

import com.project.main.domain.Post;
import com.project.main.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public Post create(String title, String content, String writer) {
        Post p = new Post(title, content, writer);
        return repository.save(p);
    }

    public List<Post> getAll() {
        return repository.findAll();
    }

    public Post getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean update(Long id, String newTitle, String newContent) {
        return repository.findById(id).map(post -> {
            post.updateContent(newTitle, newContent);
            repository.save(post);
            return true;
        }).orElse(false);
    }

    public boolean delete(Long id) {
        return repository.findById(id).map(post -> {
            repository.delete(post);
            return true;
        }).orElse(false);
    }
}
