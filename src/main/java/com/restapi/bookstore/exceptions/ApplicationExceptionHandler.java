package com.restapi.bookstore.exceptions;

import com.restapi.bookstore.exceptions.model.ApiErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApplicationExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class , NullPointerException.class})
    public ResponseEntity<Object> handleBaseException(Exception exception) {
        String errorMessage = exception.getMessage();
        if(errorMessage == null) {
            errorMessage = exception.toString();
        }
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage();
        apiErrorMessage.setMessage(errorMessage);
        apiErrorMessage.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(
                apiErrorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR
        ) ;
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String errorMessage = exception.getLocalizedMessage();
        if(errorMessage == null) {
            errorMessage = exception.toString();
        }
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage();
        apiErrorMessage.setMessage(errorMessage);
        apiErrorMessage.setStatusCode(status);
        apiErrorMessage.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(
                apiErrorMessage, status
        );
    }

    @ExceptionHandler(value = {UnauthorizedRequestException.class})
    public ResponseEntity<Object> unauthorizedRequestException(UnauthorizedRequestException exception) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage();

        String errorMessage = exception.getLocalizedMessage();

        if(errorMessage == null) {
            errorMessage = exception.toString();
        }
        apiErrorMessage.setMessage(errorMessage);
        apiErrorMessage.setStatusCode(status);
        apiErrorMessage.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(
                apiErrorMessage, status
        );
    }
}
