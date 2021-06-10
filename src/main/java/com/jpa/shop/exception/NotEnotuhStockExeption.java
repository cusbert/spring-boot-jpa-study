package com.jpa.shop.exception;

public class NotEnotuhStockExeption extends RuntimeException {

    public NotEnotuhStockExeption() {
    }

    public NotEnotuhStockExeption(String message) {
        super(message);
    }
}
