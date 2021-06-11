package com.jpa.shop.exception;

public class InvalidDeliveryStatusException extends RuntimeException {

    public InvalidDeliveryStatusException() {
    }

    public InvalidDeliveryStatusException(String message) {
        super(message);
    }
}
