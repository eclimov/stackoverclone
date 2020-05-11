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
          "SELECT " +
          "  (SELECT COUNT(*) FROM u.questions) AS questionsCount, " +
          "  (SELECT COUNT(*) FROM u.answers) AS answersCount, " +
          "  ISNULL((SELECT SUM(rq.value) FROM u.questions q INNER JOIN q.ratings rq), 0) + " +
          "    ISNULL((SELECT SUM(ra.value) FROM u.answers a INNER JOIN a.ratings ra), 0) AS rating " +
          "FROM User u " +
          "WHERE u.id = :userId "
  )
  Optional<UserStatisticsDataInterface> getUserStatistics(@Param("userId") Long userId);
}
