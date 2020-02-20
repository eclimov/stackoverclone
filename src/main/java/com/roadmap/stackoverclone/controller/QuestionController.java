package com.roadmap.stackoverclone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/questions")
public class QuestionController {
    // https://restfulapi.net/resource-naming/

    @GetMapping
    public String getQuestions(HttpServletRequest httpServletRequest) {
        return "QWERTY";
    }
}
