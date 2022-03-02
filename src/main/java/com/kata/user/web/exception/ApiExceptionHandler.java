package com.kata.user.web.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
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

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle(Exception e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
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
