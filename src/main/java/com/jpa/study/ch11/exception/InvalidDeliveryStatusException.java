package com.jpa.study.ch11.exception;

public class InvalidDeliveryStatusException extends RuntimeException {

    public InvalidDeliveryStatusException() {
    }

    public InvalidDeliveryStatusException(String message) {
        super(message);
    }
}
