package com.roadmap.stackoverclone.repository;

import com.roadmap.stackoverclone.model.entity.Answer;
import com.roadmap.stackoverclone.model.entity.RatingAnswer;
import com.roadmap.stackoverclone.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingAnswerRepository extends JpaRepository<RatingAnswer, Long> {
    Optional<RatingAnswer> findOneByAnswerAndUser(Answer answer, User user);
}
