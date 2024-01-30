package com.techlab.grocery.application.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

/**
 * The class GlobalExceptionHandler
 * <p>
 * The common exception handler for the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<Object> handleUnauthorizedException(HttpClientErrorException.Unauthorized e) {
        logger.error("Unauthorized request trial ", e);
        return e.getStatusCode().equals(HttpStatus.UNAUTHORIZED) ?
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getLocalizedMessage()) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<Object> handleNotFoundException(HttpClientErrorException.NotFound e) {
        logger.error("Resource not found for request", e);
        return e.getStatusCode().equals(HttpStatus.NOT_FOUND) ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getLocalizedMessage()) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<Object> handleNotFoundException(HttpClientErrorException.Forbidden e) {
        logger.error("Forbidden request", e);
        return e.getStatusCode().equals(HttpStatus.FORBIDDEN) ?
                ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getLocalizedMessage()) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception e) {
        logger.error("Exception occurred during the request", e);
        String errorMessage = "An unexpected error occurred during operation. Please try again later. " +
                "If the problem persists, please contact support.";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}
