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
        return repository.save(title, content, writer);
    }

    public List<Post> getAll() {
        return repository.findAll();
    }

    public Post getById(Long id) {
        return repository.findById(id);
    }

    public boolean update(Long id, String newTitle, String newContent) {
        return repository.update(id, newTitle, newContent);
    }

    public boolean delete(Long id) {
        return repository.delete(id);
    }
}
