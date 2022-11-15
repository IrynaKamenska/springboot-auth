package com.example.backend.security;

public class UsernameAlreadyExistsException extends Throwable {

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }

}
