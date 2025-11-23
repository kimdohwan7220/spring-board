package com.project.main.repository;

import com.project.main.domain.PostLike;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Optional<PostLike> findByPostIdAndUsername(Long postId, String username);

    int countByPostId(Long postId);

    void deleteByPostIdAndUsername(Long postId, String username);
}
