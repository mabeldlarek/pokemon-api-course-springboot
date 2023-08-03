package com.belarek.PokemonReviewAPI.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(PokemonNotFoundException.class)
    public ResponseEntity<ErrorObject> pokemonNotFound(PokemonNotFoundException pokemonNotFoundException, WebRequest webRequest){
        ErrorObject errorObject = new ErrorObject(HttpStatus.NOT_FOUND.value(), pokemonNotFoundException.getMessage(), new Date());
        return  new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ErrorObject> pokemonNotFound(ReviewNotFoundException pokemonNotFoundException, WebRequest webRequest){
        ErrorObject errorObject = new ErrorObject(HttpStatus.NOT_FOUND.value(), pokemonNotFoundException.getMessage(), new Date());
        return  new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }
}
