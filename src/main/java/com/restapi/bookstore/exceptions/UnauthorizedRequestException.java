package com.restapi.bookstore.exceptions;

public class UnauthorizedRequestException extends RuntimeException {

    public UnauthorizedRequestException(String message) {
        super(message);
    }
}
