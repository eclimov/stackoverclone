package com.roadmap.stackoverclone.repository;

import com.roadmap.stackoverclone.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findOneById(Long id);
}
