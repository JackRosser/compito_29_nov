package org.example.exceptions;

public class BadChoiceException extends RuntimeException {
    public BadChoiceException(String message) {
        super(message);
    }
}
