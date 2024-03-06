package com.paymentchain.mstransactions.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TransactionExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = InvalidOperationException.class)
    public ResponseEntity<String> handleInvalidOperationException(
            RuntimeException ex, WebRequest request
    ){
        return new ResponseEntity<>(
                ex.getMessage(),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = TransactionNotFoundException.class)
    public ResponseEntity<String> handleTransactionNotFoundException(
            RuntimeException ex, WebRequest request
    ){
        return new ResponseEntity<>(
                ex.getMessage(),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Object> handleCustomException(
            RuntimeException ex, WebRequest request
    ){
        return new ResponseEntity<>(
                ex.getMessage(),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
