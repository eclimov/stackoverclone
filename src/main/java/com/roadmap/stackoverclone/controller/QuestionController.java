package com.roadmap.stackoverclone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/question")
public class QuestionController {

    @GetMapping(path = "/list")
    public String getQuestions(HttpServletRequest httpServletRequest) {
        return "QWERTY";
    }
}
