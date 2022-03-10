package com.kata.user.utils.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * The {@link String} value of the country has to be France and the upper/lower case is ignored.
 * <p>
 * {@code null} elements are considered not valid.
 */
@Documented
@Constraint(validatedBy = CountryValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CountryConstraint {
    String message() default "Invalid value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
