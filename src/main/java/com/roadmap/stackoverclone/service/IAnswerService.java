package com.roadmap.stackoverclone.service;

import com.roadmap.stackoverclone.model.data.AnswerData;

import java.util.List;

public interface IAnswerService {
    List<AnswerData> findByQuestionId(Long questionId);

    AnswerData findById(Long id);

    AnswerData create(AnswerData source, Long questionId);

    AnswerData update(Long id, AnswerData source);

    boolean delete(Long id);
}