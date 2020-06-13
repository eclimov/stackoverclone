package com.roadmap.stackoverclone.service.impl;

import com.roadmap.stackoverclone.configuration.IAuthenticationFacade;
import com.roadmap.stackoverclone.exception.ForbiddenException;
import com.roadmap.stackoverclone.exception.ResourceNotFoundException;
import com.roadmap.stackoverclone.model.data.QuestionData;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
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
    public List<QuestionData> get() {
        return  questionRepository
                .findAll().stream()
                .map(e -> conversionService.convert(e, QuestionData.class))
                .collect(Collectors.toList());
    }

    @Override
    public QuestionData create(QuestionData source) {
        User user =  userRepository.findTopByUsername(
                authenticationFacade.getAuthentication().getName()
        ).orElseThrow(ResourceNotFoundException::new);
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
        User user =  userRepository.findTopByUsername(
                authenticationFacade.getAuthentication().getName()
        ).orElseThrow(ResourceNotFoundException::new);
        if (!(question.getUser().getId().equals(user.getId()) || user.hasRole("ROLE_ADMIN"))) {
            throw new ForbiddenException("You don't have access to edit this resource");
        }

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
        if (!(question.getUser().getId().equals(user.getId()) || user.hasRole("ROLE_ADMIN"))) {
            throw new ForbiddenException("You don't have access to edit this resource");
        }

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
