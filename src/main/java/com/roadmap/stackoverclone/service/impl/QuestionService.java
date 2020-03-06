package com.roadmap.stackoverclone.service.impl;

import com.roadmap.stackoverclone.model.data.QuestionData;
import com.roadmap.stackoverclone.model.entity.Question;
import com.roadmap.stackoverclone.repository.QuestionRepository;
import com.roadmap.stackoverclone.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService implements IQuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ConversionService conversionService;

    @Override
    public List<QuestionData> get() {
        return  questionRepository
                .findAll().stream()
                .map(e -> conversionService.convert(e, QuestionData.class))
                .collect(Collectors.toList());
    }

    @Override
    public QuestionData create(QuestionData source) {
        Question question = conversionService.convert(source, Question.class);
        questionRepository.save(question);
        return source.setId(question.getId());
    }

    @Override
    public QuestionData findById(Long id) {
        return conversionService.convert(
                questionRepository.findById(id).orElse(null),
                QuestionData.class
        );
    }
}
