package com.roadmap.stackoverclone.service;

import com.roadmap.stackoverclone.model.data.QuestionData;

import java.util.List;

public interface IQuestionService {
    List<QuestionData> get();

    QuestionData create(QuestionData source);

    QuestionData findById(Long id);

    QuestionData update(Long id, QuestionData source);

    void delete(Long id);
}
