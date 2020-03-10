package com.roadmap.stackoverclone.service.impl;

import com.roadmap.stackoverclone.exception.ResourceNotFoundException;
import com.roadmap.stackoverclone.model.data.AnswerData;
import com.roadmap.stackoverclone.model.entity.Answer;
import com.roadmap.stackoverclone.model.entity.Question;
import com.roadmap.stackoverclone.model.entity.User;
import com.roadmap.stackoverclone.repository.AnswerRepository;
import com.roadmap.stackoverclone.repository.QuestionRepository;
import com.roadmap.stackoverclone.repository.UserRepository;
import com.roadmap.stackoverclone.service.IAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnswerService implements IAnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversionService conversionService;

    @Override
    public List<AnswerData> findByQuestionId(Long questionId) {
        Question question = questionRepository.findOneById(questionId).orElseThrow(ResourceNotFoundException::new);

        return  answerRepository
                .findByQuestion(question).stream()
                .map(e -> conversionService.convert(e, AnswerData.class))
                .collect(Collectors.toList());
    }

    @Override
    public AnswerData findById(Long id) {
        return conversionService.convert(
                answerRepository.findById(id).orElse(null),
                AnswerData.class
        );
    }

    @Override
    public AnswerData create(AnswerData source, Long questionId) {
        Question question =  questionRepository.findOneById(questionId).orElseThrow(ResourceNotFoundException::new);
        // TODO: get user from access token instead of request's body
        User user =  userRepository.findOneById(source.getUserId()).orElseThrow(ResourceNotFoundException::new);

        Answer answer = conversionService.convert(source, Answer.class);
        answer.setQuestion(question);
        answer.setUser(user);
        answerRepository.save(answer);
        return source.setId(answer.getId());
    }

    @Override
    public AnswerData update(Long id, AnswerData source) {
        Answer answer =  answerRepository.findOneById(id).orElseThrow(ResourceNotFoundException::new);
        User user =  userRepository.findOneById(source.getUserId()).orElseThrow(ResourceNotFoundException::new);

        answer
                .setUser(user)
                .setText(source.getText());
        answerRepository.save(answer);

        return source
                .setId(user.getId());
    }

    @Override
    public boolean delete(Long id) {
        Optional<Answer> answer = answerRepository.findById(id);
        if (answer.isPresent()) {
            answerRepository.delete(answer.get());
            return true;
        }

        return false;
    }
}
