package com.kata.user.utils.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * This Validator class checks that a given date is a well-formed date and should be before 18 years ago.
 */
public class BirthdayValidator implements ConstraintValidator<BirthdayConstraint, LocalDate> {

    @Override
    public boolean isValid(LocalDate birthdate, ConstraintValidatorContext cxt) {

        return birthdate != null && birthdate.isBefore(LocalDate.now().minusYears(18));
    }

}