package com.paymentchain.msproduct.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(
        RuntimeException ex, WebRequest request
    ){
        return new ResponseEntity<>(
            ex.getMessage(),
            new HttpHeaders(),
            HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(RuntimeException.class)
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
