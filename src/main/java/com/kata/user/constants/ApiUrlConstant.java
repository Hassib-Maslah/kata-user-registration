package com.kata.user.constants;

public final class ApiUrlConstant {

    public final static String USER_PREFIX_API = "/user-management";
    public final static String USERS_API = USER_PREFIX_API + "/users";
    public final static String USERS_DETAILS_API = USERS_API + "/{id}";

    private ApiUrlConstant() {
        throw new UnsupportedOperationException("This is a class contains only constants and cannot be instantiated");
    }

}
