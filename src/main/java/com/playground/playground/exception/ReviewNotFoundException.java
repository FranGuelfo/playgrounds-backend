package com.playground.playground.exception;

public class ReviewNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Review not found";

    public ReviewNotFoundException() {
        super(MESSAGE);
    }
}
