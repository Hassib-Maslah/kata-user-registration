package com.kata.user.constants;

public final class ErrorMessageConstant {
    public final static String VALIDATION_ERROR_MSG = "Request failed due to a validation error";

    public final static String INVALID_FORMAT_ERROR_MSG = "Request failed due to an invalid format error";

    public final static String VALIDATION_MANDATORY_MSG = "This field is mandatory";

    public final static String VALIDATION_COUNTRY_MSG = "France is the only valid value";

    public final static String VALIDATION_BIRTHDAY_MSG = "Only adult users accepted (+18)";

    private ErrorMessageConstant() {
        throw new UnsupportedOperationException("This is a class contains only constants and cannot be instantiated");
    }

}
