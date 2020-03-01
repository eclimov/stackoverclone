package com.roadmap.stackoverclone.controller;

import com.roadmap.stackoverclone.model.data.UserData;
import com.roadmap.stackoverclone.service.impl.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserData>> get() {
        List<UserData> userData = userService.get();

        return userData.isEmpty() ? ResponseEntity.notFound().build()
            : ResponseEntity.ok(userData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserData> find(@PathVariable("id") Long id) {
        UserData result = userService.findById(id);
        return result == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<UserData> create(@RequestBody UserData userData) {
        return ResponseEntity.ok(userService.create(userData));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserData> update(
        @PathVariable("id") Long id,
        @RequestBody UserData userData
    ) {
        return ResponseEntity.ok(
            userService.update(id, userData)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        return userService.delete(id)
            ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
