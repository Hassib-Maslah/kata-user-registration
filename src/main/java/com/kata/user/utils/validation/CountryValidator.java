package com.kata.user.utils.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;

public class CountryValidator implements ConstraintValidator<CountryConstraint, String> {

    @Override
    public void initialize(CountryConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext cxt) {

        return contactField != null && contactField.equalsIgnoreCase(Locale.FRANCE.getDisplayCountry());
    }

}