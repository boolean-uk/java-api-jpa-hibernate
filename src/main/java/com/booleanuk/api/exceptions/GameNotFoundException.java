package com.booleanuk.api.exceptions;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(Long gameId) {
        super("Game not found with ID: " + gameId);
    }
}