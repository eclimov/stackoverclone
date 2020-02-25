package com.roadmap.stackoverclone.controller;

import com.roadmap.stackoverclone.model.data.UserData;
import com.roadmap.stackoverclone.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private EntityManager entityManager;

    @PostMapping
    public ResponseEntity<UserData> create(@RequestBody UserData userData) {
        // TODO: move this to converter
        User user = new User()
                .setName(userData.getName());

        // TODO: move this to service/repository
        entityManager.persist(user);
        entityManager.flush();

        // TODO: move this to converter
        userData.setId(user.getId());

        return ResponseEntity.ok(userData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserData> find(@PathVariable("id") Long id) {
        // TODO: move this to service/repository
        User user = entityManager.find(User.class, id);

        // TODO: handle 'NOT FOUND' case

        // TODO: move this to converter
        UserData userData = new UserData()
                .setId(user.getId())
                .setName(user.getName());

        return ResponseEntity.ok(userData);
    }
}
