package com.roadmap.stackoverclone.controller;

import com.roadmap.stackoverclone.model.data.UserData;
import com.roadmap.stackoverclone.model.data.UserStatisticsDataInterface;
import com.roadmap.stackoverclone.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<UserData>> get() {
        List<UserData> userData = userService.get();

        return ResponseEntity.ok(userData);
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
        return ResponseEntity.ok(userService.update(id, userData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        return userService.delete(id)
            ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/stats")
    public ResponseEntity<UserStatisticsDataInterface> getStats(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserStatistics(id));
    }
}
