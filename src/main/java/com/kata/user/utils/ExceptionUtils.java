package com.kata.user.utils;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

/**
 * This is an Exception utility class that should contain only static helper methods.
 */
public final class ExceptionUtils {

    private ExceptionUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Map<String, String> getMappedFieldError(BindException e) {
        Map<String, String> data = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            data.put(error.getField(), error.getDefaultMessage());
        }
        return data;
    }

}
