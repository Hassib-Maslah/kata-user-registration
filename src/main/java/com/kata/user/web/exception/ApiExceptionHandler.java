package com.kata.user.web.exception;

import com.kata.user.model.ErrorResponse;
import com.kata.user.utils.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.kata.user.constants.ErrorMessageConstant.*;

/**
 * This is a REST Controller Advice dedicated to handle all types of exception.
 * Rest Controller Advice’s methods (annotated with {@link ExceptionHandler}) are shared globally across multiple {@link org.springframework.web.bind.annotation.RestController} components to capture exceptions and translate them to HTTP responses.
 * The {@link ExceptionHandler} annotation indicates which type of Exception we want to handle.
 * The exception instance and the request will be injected via method arguments.
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle(Exception e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(UserNotFoundException e) {
        ErrorResponse response = new ErrorResponse(DATA_NOT_FOUND_ERROR_MSG, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handle(BindException e) {
        ErrorResponse response = new ErrorResponse(VALIDATION_ERROR_MSG, ExceptionUtils.getMappedFieldError(e));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handle(HttpMessageNotReadableException e) {
        ErrorResponse response = new ErrorResponse(INVALID_FORMAT_ERROR_MSG, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handle(DataIntegrityViolationException e) {
        ErrorResponse response = new ErrorResponse(ALREADY_EXIST_DATA_ERROR_MSG, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

}
