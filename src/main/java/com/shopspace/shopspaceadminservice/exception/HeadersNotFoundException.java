package com.shopspace.shopspaceadminservice.exception;

public class HeadersNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public HeadersNotFoundException(String message) {
        super(message);
    }
}
