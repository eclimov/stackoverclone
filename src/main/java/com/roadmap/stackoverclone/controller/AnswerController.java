package com.roadmap.stackoverclone.controller;

import com.roadmap.stackoverclone.model.data.AnswerData;
import com.roadmap.stackoverclone.service.impl.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/answers")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<AnswerData>> get(@PathVariable("questionId") Long questionId) {
        List<AnswerData> answerData = answerService.findByQuestionId(questionId);

        return ResponseEntity.ok(answerData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerData> find(@PathVariable("id") Long id) {
        AnswerData result = answerService.findById(id);
        return result == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
    }

    @PostMapping("/question/{questionId}")
    public ResponseEntity<AnswerData> create(@RequestBody AnswerData answerData, @PathVariable("questionId") Long questionId) {
        return ResponseEntity.ok(answerService.create(answerData, questionId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnswerData> update(
            @PathVariable("id") Long id,
            @RequestBody AnswerData answerData
    ) {
        return ResponseEntity.ok(answerService.update(id, answerData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        answerService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/voteup")
    public ResponseEntity<Void> voteUp(@PathVariable("id") Long id) {
        answerService.voteUp(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/votedown")
    public ResponseEntity<Void> voteDown(@PathVariable("id") Long id) {
        answerService.voteDown(id);
        return ResponseEntity.ok().build();
    }
}