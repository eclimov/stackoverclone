package com.roadmap.stackoverclone.validator;

import com.roadmap.stackoverclone.configuration.IAuthenticationFacade;
import com.roadmap.stackoverclone.constant.EntityConstants;
import com.roadmap.stackoverclone.constant.RoleConstants;
import com.roadmap.stackoverclone.constraint.VerifyRightsParameters;
import com.roadmap.stackoverclone.exception.ResourceNotFoundException;
import com.roadmap.stackoverclone.model.entity.BaseTextEntity;
import com.roadmap.stackoverclone.model.entity.User;
import com.roadmap.stackoverclone.repository.AnswerRepository;
import com.roadmap.stackoverclone.repository.QuestionRepository;
import com.roadmap.stackoverclone.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class VerifyRightsParametersValidator
        implements ConstraintValidator<VerifyRightsParameters, Object[]> {

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserRepository userRepository;

    private String objectType;

    public void initialize(VerifyRightsParameters constraintAnnotation) {
        this.objectType = constraintAnnotation.objectType();
    }

    @SneakyThrows
    @Override
    public boolean isValid(Object[] objects, ConstraintValidatorContext constraintValidatorContext) {
        JpaRepository repository;
        if (objectType.equals(EntityConstants.TYPE_QUESTIONS)) {
            repository = questionRepository;
        } else {
            repository = answerRepository;
        }
        BaseTextEntity textEntity = (BaseTextEntity) repository.findById((Long) objects[0]).orElseThrow(ResourceNotFoundException::new);

        User user =  userRepository.findTopByUsername(
                authenticationFacade.getAuthentication().getName()
        ).orElseThrow(ResourceNotFoundException::new);

        return textEntity.getUser().getId().equals(user.getId()) || user.hasRole(RoleConstants.ADMIN);
    }
}
