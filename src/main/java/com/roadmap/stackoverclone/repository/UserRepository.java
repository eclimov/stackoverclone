package com.roadmap.stackoverclone.repository;

import com.roadmap.stackoverclone.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findOneById(Long id);
}
