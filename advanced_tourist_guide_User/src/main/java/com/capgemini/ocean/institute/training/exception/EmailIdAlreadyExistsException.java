package com.capgemini.ocean.institute.training.exception;

public class EmailIdAlreadyExistsException extends RuntimeException {

    public EmailIdAlreadyExistsException() {
        super(String.format("Email already exists!"));
    }
}
