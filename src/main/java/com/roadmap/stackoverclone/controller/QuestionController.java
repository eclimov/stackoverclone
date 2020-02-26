package com.roadmap.stackoverclone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/questions")
public class QuestionController {
    // https://restfulapi.net/resource-naming/

    @GetMapping
    public String get(HttpServletRequest httpServletRequest) {
        return "QWERTY";
    }

    @GetMapping("/{id}")
    public int find(@PathVariable("id") int id) {
        return id;
    }
}
