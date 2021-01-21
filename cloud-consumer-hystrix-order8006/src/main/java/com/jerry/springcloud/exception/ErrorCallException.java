package com.jerry.springcloud.exception;

public class ErrorCallException extends RuntimeException {
    public ErrorCallException(String message, Object... args) {
        super(String.format(message, args));
    }

    @Override
    public String toString() {
        return String.format("ErrorCallException: %s", this.getMessage());
    }
}
