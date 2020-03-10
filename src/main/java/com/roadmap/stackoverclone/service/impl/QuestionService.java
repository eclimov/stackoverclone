package com.roadmap.stackoverclone.service.impl;

import com.roadmap.stackoverclone.exception.ResourceNotFoundException;
import com.roadmap.stackoverclone.model.data.QuestionData;
import com.roadmap.stackoverclone.model.entity.Question;
import com.roadmap.stackoverclone.model.entity.User;
import com.roadmap.stackoverclone.repository.QuestionRepository;
import com.roadmap.stackoverclone.repository.UserRepository;
import com.roadmap.stackoverclone.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService implements IQuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

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
    public QuestionData create(QuestionData source, Long userId) {
        User user =  userRepository.findOneById(userId).orElseThrow(ResourceNotFoundException::new);
        Question question = conversionService.convert(source, Question.class);
        question.setUser(user);

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

    @Override
    public QuestionData update(Long id, QuestionData source) {
        Question question =  questionRepository.findOneById(id).orElseThrow(ResourceNotFoundException::new);
        question.setText(source.getText());
        // TODO: update list of answers here
        questionRepository.save(question);

        return source
                .setId(question.getId());
    }

    @Override
    public boolean delete(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            questionRepository.delete(question.get());
            return true;
        }

        return false;
    }
}
