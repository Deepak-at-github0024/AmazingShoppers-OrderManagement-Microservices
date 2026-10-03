package com.example.order_service.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderDetailsNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderException(
            OrderDetailsNotFoundException ex , HttpServletRequest request )
    {

        ErrorResponse err = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Order Service Thrown Some Exception",
                ex.getMessage(),
                request.getRequestURI()
        );

     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err );

    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductException(
            ProductNotFoundException ex , HttpServletRequest request )
    {

        ErrorResponse err = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Product Do not exits",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err );

    }

    @ExceptionHandler(UsersPrincipalNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserException(
            UsersPrincipalNotFoundException ex , HttpServletRequest request )


    {

        ErrorResponse err = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "User do not exists",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err );

    }





}
