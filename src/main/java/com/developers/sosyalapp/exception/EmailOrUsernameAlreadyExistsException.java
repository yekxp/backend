package com.developers.sosyalapp.exception;

public class EmailOrUsernameAlreadyExistsException extends RuntimeException {
    public EmailOrUsernameAlreadyExistsException(String message) {
        super(message);
    }
}
