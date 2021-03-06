package com.kata.user.utils.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;

/**
 * This Validator class checks that a given country is France.
 */
public class CountryValidator implements ConstraintValidator<CountryConstraint, String> {

    @Override
    public boolean isValid(String country, ConstraintValidatorContext cxt) {

        return country != null && country.equalsIgnoreCase(Locale.FRANCE.getDisplayCountry());
    }

}