package com.roadmap.stackoverclone.repository;

import com.roadmap.stackoverclone.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
