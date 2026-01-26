package edu.alatoo.GST.data.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.alatoo.GST.data.models.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{
    Optional<Subject> findById(Long id);
    Optional<Subject> findByTitle(String title);
    boolean existsByTitle(String title);
}
