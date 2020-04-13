package com.roadmap.stackoverclone.repository;

import com.roadmap.stackoverclone.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findOneById(Long id);

  User findByUsername(String username);
}
