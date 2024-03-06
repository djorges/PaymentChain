package com.paymentchain.mstransactions.exception;

public class InvalidOperationException extends RuntimeException{
    public InvalidOperationException(String message) {
        super(message);
    }
    public InvalidOperationException(Throwable cause) {
        super(cause);
    }
    public InvalidOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
