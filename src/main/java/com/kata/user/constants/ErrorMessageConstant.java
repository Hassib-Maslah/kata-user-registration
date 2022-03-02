package com.kata.user.constants;

public final class ErrorMessageConstant {
    public final static String VALIDATION_ERROR_MSG = "Request failed due to a validation error";

    public final static String VALIDATION_MANDATORY_MSG = "This field is mandatory";

    private ErrorMessageConstant() {
        throw new UnsupportedOperationException("This is a class contains only constants and cannot be instantiated");
    }

}
