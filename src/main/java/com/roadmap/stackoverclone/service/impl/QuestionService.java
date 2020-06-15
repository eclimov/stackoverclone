package com.roadmap.stackoverclone.service.impl;

import com.roadmap.stackoverclone.configuration.IAuthenticationFacade;
import com.roadmap.stackoverclone.exception.ResourceNotFoundException;
import com.roadmap.stackoverclone.model.data.TextData;
import com.roadmap.stackoverclone.model.entity.Question;
import com.roadmap.stackoverclone.model.entity.RatingQuestion;
import com.roadmap.stackoverclone.model.entity.User;
import com.roadmap.stackoverclone.repository.QuestionRepository;
import com.roadmap.stackoverclone.repository.RatingQuestionRepository;
import com.roadmap.stackoverclone.repository.UserRepository;
import com.roadmap.stackoverclone.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class QuestionService implements IQuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private RatingQuestionRepository ratingQuestionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Override
    public List<TextData> get() {
        return  questionRepository
                .findAll().stream()
                .map(e -> conversionService.convert(e, TextData.class))
                .collect(Collectors.toList());
    }

    @Override
    public TextData create(TextData source) {
        User user =  userRepository.findTopByUsername(
                authenticationFacade.getAuthentication().getName()
        ).orElseThrow(ResourceNotFoundException::new);
        Question question = conversionService.convert(source, Question.class);
        question.setUser(user);

        questionRepository.save(question);
        return source.setId(question.getId());
    }

    @Override
    public TextData findById(Long id) {
        return conversionService.convert(
                questionRepository.findById(id).orElse(null),
                TextData.class
        );
    }

    @Override
    public TextData update(Long id, TextData source) {
        Question question =  questionRepository.findOneById(id).orElseThrow(ResourceNotFoundException::new);
        User user =  userRepository.findTopByUsername(
                authenticationFacade.getAuthentication().getName()
        ).orElseThrow(ResourceNotFoundException::new);

        question.setText(source.getText());
        // TODO: update list of answers here
        questionRepository.save(question);

        return source
                .setId(question.getId());
    }

    @Override
    public void delete(Long id) {
        Question question =  questionRepository.findOneById(id).orElseThrow(ResourceNotFoundException::new);
        User user =  userRepository.findTopByUsername(
                authenticationFacade.getAuthentication().getName()
        ).orElseThrow(ResourceNotFoundException::new);

        questionRepository.delete(question);
    }

    private RatingQuestion prepareRating(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        User user =  userRepository.findTopByUsername(
                authenticationFacade.getAuthentication().getName()
        ).orElseThrow(ResourceNotFoundException::new);

        return ratingQuestionRepository.findOneByQuestionAndUser(question, user).orElse(
                (new RatingQuestion())
                        .setQuestion(question)
                        .setUser(user)
        );
    }

    @Override
    public void voteUp(Long id) {
        ratingQuestionRepository.save(
                this.prepareRating(id).setValue(1)
        );
    }

    @Override
    public void voteDown(Long id) {
        ratingQuestionRepository.save(
                this.prepareRating(id).setValue(-1)
        );
    }
}
