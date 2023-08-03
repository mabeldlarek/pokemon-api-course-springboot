package com.belarek.PokemonReviewAPI.api.exceptions;

public class PokemonNotFoundException extends RuntimeException {
    public PokemonNotFoundException(String message) {
        super(message);
    }
}
