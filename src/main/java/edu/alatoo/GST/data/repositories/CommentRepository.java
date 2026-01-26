package edu.alatoo.GST.data.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.alatoo.GST.data.models.Comment;
import edu.alatoo.GST.data.models.Educator;
import edu.alatoo.GST.data.models.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndEducator(Long id, Educator educator);
    Page<Comment> findAllByEducator(Educator educator, Pageable pageable);
    boolean existsByIdAndEducator(Long id, Educator educator);
    boolean existsByEducatorAndUser(Educator educator, User user);
    void deleteByIdAndEducator(Long id, Educator educator);
}
