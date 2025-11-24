package com.project.main.service;

import com.project.main.domain.Comment;
import com.project.main.domain.Post;
import com.project.main.domain.User;
import com.project.main.dto.response.CommentResponse;
import com.project.main.exception.ResourceNotFoundException;
import com.project.main.repository.CommentRepository;
import com.project.main.repository.PostRepository;
import com.project.main.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository,
                          PostRepository postRepository,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public CommentResponse create(Long postId, String writer, String content) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post not found"));

        Comment comment = new Comment(post, writer, content);
        Comment saved = commentRepository.save(comment);

        post.increaseCommentCount();
        postRepository.save(post);

        String profileImage = findProfileImage(writer);

        return CommentResponse.from(saved, profileImage);
    }

    public List<CommentResponse> getComments(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post not found"));

        List<Comment> comments = commentRepository.findByPost(post);

        return comments.stream()
                .map(c -> {
                    String profileImage = findProfileImage(c.getWriter());
                    return CommentResponse.from(c, profileImage);
                })
                .toList();
    }

    public void delete(Long postId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comment not found"));

        if (!comment.getPost().getId().equals(postId)) {
            throw new IllegalArgumentException("댓글이 해당 게시글에 속하지 않습니다.");
        }

        Post post = comment.getPost();

        commentRepository.delete(comment);

        post.decreaseCommentCount();
        postRepository.save(post);
    }

    public CommentResponse update(Long postId, Long commentId, String newContent) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comment not found"));

        if (!comment.getPost().getId().equals(postId)) {
            throw new IllegalArgumentException("댓글이 해당 게시글에 속하지 않습니다.");
        }

        comment.updateContent(newContent);
        Comment saved = commentRepository.save(comment);

        String profileImage = findProfileImage(saved.getWriter());

        return CommentResponse.from(saved, profileImage);
    }

    private String findProfileImage(String username) {
        return userRepository.findByUsername(username)
                .map(User::getProfileImage)
                .orElse(null);
    }
}
