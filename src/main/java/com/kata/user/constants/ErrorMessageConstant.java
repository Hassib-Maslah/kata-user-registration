package com.kata.user.constants;

public final class ErrorMessageConstant {
    public final static String VALIDATION_ERROR_MSG = "Request failed due to a validation error";

    public final static String VALIDATION_MANDATORY_MSG = "This field is mandatory";

    public final static String VALIDATION_COUNTRY_MSG = "France is the only valid value";

    private ErrorMessageConstant() {
        throw new UnsupportedOperationException("This is a class contains only constants and cannot be instantiated");
    }

}
