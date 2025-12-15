package com.playground.playground.exception;

public class PlaygroundNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Playground not found";

    public PlaygroundNotFoundException() {
        super(MESSAGE);
    }
}
