package com.roadmap.stackoverclone.repository;

import com.roadmap.stackoverclone.model.data.UserStatisticsDataInterface;
import com.roadmap.stackoverclone.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findOneById(Long id);

  Optional<User> findByUsername(String username);

  @Query(
          value = "SELECT " +
                  "  u.id AS user_id, " +
                  "  ISNULL(q_stats.q_count, 0) AS questionsCount, " +
                  "  ISNULL(a_stats.a_count, 0) AS answersCount, " +
                  "  ISNULL(q_stats.rating, 0) + ISNULL(a_stats.rating, 0) AS rating " +
                  "FROM users AS u " +
                  "LEFT JOIN ( " +
                  "  SELECT " +
                  "    COUNT(q.id) as q_count, " +
                  "    SUM(rq.value) AS rating, " +
                  "    q.user_id " +
                  "  FROM questions q " +
                  "  LEFT JOIN rating_question rq " +
                  "  ON rq.question_id = q.id " +
                  "  WHERE q.user_id = :userId " +
                  "  GROUP BY q.user_id " +
                  ") AS q_stats " +
                  "ON q_stats.user_id = u.id " +
                  "LEFT JOIN ( " +
                  "  SELECT " +
                  "    COUNT(a.id) as a_count, " +
                  "    SUM(ra.value) AS rating, " +
                  "    a.user_id " +
                  "  FROM answers a " +
                  "  LEFT JOIN rating_answer ra " +
                  "  ON ra.answer_id = a.id " +
                  "  WHERE a.user_id = :userId " +
                  "  GROUP BY a.user_id " +
                  ") AS a_stats " +
                  "ON a_stats.user_id = u.id " +
                  "WHERE u.id = :userId",
          nativeQuery = true
  )
  Optional<UserStatisticsDataInterface> getUserStatistics(@Param("userId") Long userId);
}
