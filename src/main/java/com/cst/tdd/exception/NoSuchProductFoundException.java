package com.cst.tdd.exception;

public class NoSuchProductFoundException extends Exception {
    public NoSuchProductFoundException(String message) {
        super(message);
    }
}
