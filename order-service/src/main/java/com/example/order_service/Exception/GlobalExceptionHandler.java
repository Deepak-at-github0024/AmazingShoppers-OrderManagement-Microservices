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
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(
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




}
