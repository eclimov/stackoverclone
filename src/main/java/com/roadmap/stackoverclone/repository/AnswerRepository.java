package com.roadmap.stackoverclone.repository;

import com.roadmap.stackoverclone.model.entity.Answer;
import com.roadmap.stackoverclone.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findOneById(Long id);

    List<Answer> findByQuestion(Question question);
}
