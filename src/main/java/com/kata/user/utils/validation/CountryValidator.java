package com.kata.user.utils.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;

public class CountryValidator implements ConstraintValidator<CountryConstraint, String> {

    @Override
    public void initialize(CountryConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String country, ConstraintValidatorContext cxt) {

        return country != null && country.equalsIgnoreCase(Locale.FRANCE.getDisplayCountry());
    }

}