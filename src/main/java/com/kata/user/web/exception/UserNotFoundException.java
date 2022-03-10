package com.kata.user.web.exception;

/**
 * This is custom {@link RuntimeException} that should be thrown when ever a user not found in the database.
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

}
