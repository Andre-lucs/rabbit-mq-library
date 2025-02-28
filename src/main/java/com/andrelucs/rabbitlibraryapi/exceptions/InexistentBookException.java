package com.andrelucs.rabbitlibraryapi.exceptions;


public class InexistentBookException extends RuntimeException {

    public InexistentBookException(String message) {
        super(message);
    }
}
