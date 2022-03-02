package com.kata.user.utils.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class BirthdayValidator implements ConstraintValidator<BirthdayConstraint, LocalDate> {

    @Override
    public void initialize(BirthdayConstraint contactNumber) {
    }

    @Override
    public boolean isValid(LocalDate birthdate, ConstraintValidatorContext cxt) {

        return birthdate != null && birthdate.isBefore(LocalDate.now().minusYears(18));
    }

}