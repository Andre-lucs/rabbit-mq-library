package com.andrelucs.rabbitlibraryapi.config;

import com.andrelucs.rabbitlibraryapi.exceptions.InexistentBookException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(InexistentBookException.class)
    public ResponseEntity<String> handleInexistentBookException(InexistentBookException e, WebRequest request){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e, WebRequest request){
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<String> handleJsonProcessingException(JsonProcessingException e, WebRequest request){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
