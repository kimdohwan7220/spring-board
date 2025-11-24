package com.project.main.service;

import com.project.main.domain.Post;
import com.project.main.domain.PostLike;
import com.project.main.exception.ResourceNotFoundException;
import com.project.main.repository.PostLikeRepository;
import com.project.main.repository.PostRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository repository;
    private final PostLikeRepository likeRepository;

    public PostService(PostRepository repository, PostLikeRepository likeRepository) {
        this.repository = repository;
        this.likeRepository = likeRepository;
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

    @Transactional
    public Post increaseViews(Long id) {
        Post post = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("post not found"));
        post.increaseViews();
        return post;
    }

    @Transactional
    public Post toggleLike(Long postId, String username) {
        Post post = repository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post not found"));

        Optional<PostLike> likeOpt = likeRepository.findByPostIdAndUsername(postId, username);

        if (likeOpt.isPresent()) {
            likeRepository.deleteByPostIdAndUsername(postId, username);
        } else {
            likeRepository.save(new PostLike(postId, username));
        }

        int likeCount = likeRepository.countByPostId(postId);
        post.setLikes(likeCount);

        return repository.save(post);
    }

    public boolean isLiked(Long postId, String username) {
        if (username == null || username.isBlank()) return false;
        return likeRepository.findByPostIdAndUsername(postId, username).isPresent();
    }
}
