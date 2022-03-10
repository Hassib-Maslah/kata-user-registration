package com.kata.user.utils.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.time.LocalDate;

/**
 * The {@link LocalDate} has to be a well-formed date yyyy-mm-dd and should be before 18 years ago.
 * <p>
 * {@code null} elements are considered not valid.
 */
@Documented
@Constraint(validatedBy = BirthdayValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BirthdayConstraint {
    String message() default "Invalid value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
