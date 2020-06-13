package com.roadmap.stackoverclone.service.impl;

import com.roadmap.stackoverclone.configuration.IAuthenticationFacade;
import com.roadmap.stackoverclone.exception.ForbiddenException;
import com.roadmap.stackoverclone.exception.ResourceNotFoundException;
import com.roadmap.stackoverclone.model.data.AnswerData;
import com.roadmap.stackoverclone.model.entity.Answer;
import com.roadmap.stackoverclone.model.entity.Question;
import com.roadmap.stackoverclone.model.entity.RatingAnswer;
import com.roadmap.stackoverclone.model.entity.User;
import com.roadmap.stackoverclone.repository.AnswerRepository;
import com.roadmap.stackoverclone.repository.QuestionRepository;
import com.roadmap.stackoverclone.repository.RatingAnswerRepository;
import com.roadmap.stackoverclone.repository.UserRepository;
import com.roadmap.stackoverclone.service.IAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService implements IAnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private RatingAnswerRepository ratingAnswerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

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
        User user =  userRepository.findTopByUsername(
                authenticationFacade.getAuthentication().getName()
        ).orElseThrow(ResourceNotFoundException::new);
        Question question =  questionRepository.findOneById(questionId).orElseThrow(ResourceNotFoundException::new);

        Answer answer = conversionService.convert(source, Answer.class);
        answer.setQuestion(question);
        answer.setUser(user);
        answerRepository.save(answer);
        return source.setId(answer.getId());
    }

    @Override
    public AnswerData update(Long id, AnswerData source) {
        Answer answer = answerRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        User user =  userRepository.findTopByUsername(
                authenticationFacade.getAuthentication().getName()
        ).orElseThrow(ResourceNotFoundException::new);
        if (!(answer.getUser().getId().equals(user.getId()) || user.hasRole("ROLE_ADMIN"))) {
            throw new ForbiddenException("You don't have access to edit this resource");
        }


        answer.setText(source.getText());
        answerRepository.save(answer);

        return source
                .setId(user.getId());
    }

    @Override
    public void delete(Long id) {
        Answer answer = answerRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        User user =  userRepository.findTopByUsername(
                authenticationFacade.getAuthentication().getName()
        ).orElseThrow(ResourceNotFoundException::new);
        if (!(answer.getUser().getId().equals(user.getId()) || user.hasRole("ROLE_ADMIN"))) {
            throw new ForbiddenException("You don't have access to edit this resource");
        }

        answerRepository.delete(answer);
    }

    private RatingAnswer prepareRating(Long id) {
        Answer answer = answerRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        User user =  userRepository.findTopByUsername(
                authenticationFacade.getAuthentication().getName()
        ).orElseThrow(ResourceNotFoundException::new);

        return ratingAnswerRepository.findOneByAnswerAndUser(answer, user).orElse(
                (new RatingAnswer())
                        .setAnswer(answer)
                        .setUser(user)
        );
    }

    @Override
    public void voteUp(Long id) {
        ratingAnswerRepository.save(
                this.prepareRating(id).setValue(1)
        );
    }

    @Override
    public void voteDown(Long id) {
        ratingAnswerRepository.save(
                this.prepareRating(id).setValue(-1)
        );
    }
}
