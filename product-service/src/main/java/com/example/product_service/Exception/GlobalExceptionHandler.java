package com.example.product_service.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException
            (ProductNotFoundException exc , HttpServletRequest request)
    {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Product Thrown Some Exception",
                exc.getMessage(),
                request.getRequestURI()
        ) ;

     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

    }
}
