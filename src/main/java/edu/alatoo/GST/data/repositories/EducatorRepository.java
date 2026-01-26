package edu.alatoo.GST.data.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.alatoo.GST.data.models.Educator;

@Repository
public interface EducatorRepository extends JpaRepository<Educator, Long> {
    Optional<Educator> findById(Long id);
}
