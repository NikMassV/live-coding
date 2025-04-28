package org.example.refactoring.order.corrected.exception;

public class OrderValidationException extends RuntimeException {

    public OrderValidationException(String message) {
        super(message);
    }
}
