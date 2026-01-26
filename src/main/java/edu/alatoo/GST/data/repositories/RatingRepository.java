package edu.alatoo.GST.data.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.alatoo.GST.data.models.Educator;
import edu.alatoo.GST.data.models.Rating;
import edu.alatoo.GST.data.models.User;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByIdAndEducator(Long id, Educator educator);
    Page<Rating> findAllByEducator(Educator educator,Pageable pageable);
    boolean existsByEducatorAndUser(Educator educator, User user);
    boolean existsByIdAndEducator(Long id, Educator educator);
    void deleteByIdAndEducator(Long id, Educator educator);
}
