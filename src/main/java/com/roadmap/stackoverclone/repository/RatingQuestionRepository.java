package com.roadmap.stackoverclone.repository;

import com.roadmap.stackoverclone.model.entity.Question;
import com.roadmap.stackoverclone.model.entity.RatingQuestion;
import com.roadmap.stackoverclone.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingQuestionRepository extends JpaRepository<RatingQuestion, Long> {
    Optional<RatingQuestion> findOneByQuestionAndUser(Question question, User user);
}
