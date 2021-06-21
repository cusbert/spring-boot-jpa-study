package com.jpa.study.ch12.exception;

public class InvalidDeliveryStatusException extends RuntimeException {

    public InvalidDeliveryStatusException() {
    }

    public InvalidDeliveryStatusException(String message) {
        super(message);
    }
}
