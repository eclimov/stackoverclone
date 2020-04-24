package com.roadmap.stackoverclone.controller;

import com.roadmap.stackoverclone.model.data.QuestionData;
import com.roadmap.stackoverclone.service.impl.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<QuestionData>> get() {
        List<QuestionData> questionData = questionService.get();

        return ResponseEntity.ok(questionData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionData> find(@PathVariable("id") Long id) {
        QuestionData result = questionService.findById(id);
        return result == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<QuestionData> create(@RequestBody QuestionData questionData) {
        return ResponseEntity.ok(questionService.create(questionData));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionData> update(
            @PathVariable("id") Long id,
            @RequestBody QuestionData questionData
    ) {
        return ResponseEntity.ok(questionService.update(id, questionData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        questionService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/voteup")
    public ResponseEntity<Void> voteUp(@PathVariable("id") Long id) {
        questionService.voteUp(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/votedown")
    public ResponseEntity<Void> voteDown(@PathVariable("id") Long id) {
        questionService.voteDown(id);
        return ResponseEntity.ok().build();
    }
}
