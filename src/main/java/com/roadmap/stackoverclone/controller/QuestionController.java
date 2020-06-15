package com.roadmap.stackoverclone.controller;

import com.roadmap.stackoverclone.model.data.TextData;
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
    public ResponseEntity<List<TextData>> get() {
        List<TextData> questionData = questionService.get();

        return ResponseEntity.ok(questionData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TextData> find(@PathVariable("id") Long id) {
        TextData result = questionService.findById(id);
        return result == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<TextData> create(@RequestBody TextData questionData) {
        return ResponseEntity.ok(questionService.create(questionData));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TextData> update(
            @PathVariable("id") Long id,
            @RequestBody TextData questionData
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
