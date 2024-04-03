package com.kinduberre.kindupay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class MissingAuthHeaderException extends RuntimeException{
    // Runtime exception just needs this, I guess :/
    private static final long serialVersionUID = 1L;

    public MissingAuthHeaderException(String message){
        super(message);
    }
}
