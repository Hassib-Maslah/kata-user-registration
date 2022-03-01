package com.kata.user.constants;

public final class ApiUrlConstant {

    public final static String USER_PREFIX_API = "/user-management";
    public final static String USER_REGISTRATION_API = USER_PREFIX_API + "/users";

    private ApiUrlConstant() {
        throw new UnsupportedOperationException("This is a class contains only constants and cannot be instantiated");
    }

}
