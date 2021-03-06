package com.kata.user.constants;

/**
 * This class contain only URL constants for REST APIs.
 */
public final class ApiUrlConstant {

    public static final String USER_PREFIX_API = "/user-management";
    public static final String USERS_API = USER_PREFIX_API + "/users";
    public static final String USERS_DETAILS_API = USERS_API + "/{id}";

    private ApiUrlConstant() {
        throw new UnsupportedOperationException("This is a class contains only constants and cannot be instantiated");
    }

}
