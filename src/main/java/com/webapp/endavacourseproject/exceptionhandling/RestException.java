package com.webapp.endavacourseproject.exceptionhandling;

import org.springframework.http.HttpStatus;

public class RestException extends Exception{

    private final HttpStatus status;

    public RestException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
