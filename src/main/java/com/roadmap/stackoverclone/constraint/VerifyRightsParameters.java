package com.roadmap.stackoverclone.constraint;

import com.roadmap.stackoverclone.validator.VerifyRightsParametersValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = VerifyRightsParametersValidator.class)
@Target({ METHOD, CONSTRUCTOR })
@Retention(RUNTIME)
@Documented
public @interface VerifyRightsParameters {

    String objectType();

    String message() default
            "You have no access to this action";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
