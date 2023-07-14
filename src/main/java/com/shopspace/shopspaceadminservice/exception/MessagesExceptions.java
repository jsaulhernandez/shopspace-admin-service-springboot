package com.shopspace.shopspaceadminservice.exception;

public class MessagesExceptions {
    private MessagesExceptions() {
        throw new IllegalStateException("Utility class");
    }

    public static final String BAD_CREDENTIALS = "The session is invalid";
    public static final String INVALID_TOKEN = "Token isn't valid";
    public static final String EXCEPTION_TOKEN = "Unable to get JWT Token or JWT Token has expired";
    public static final String HEADERS_NOT_FOUND = "The server does not meet one of the preconditions";
}
