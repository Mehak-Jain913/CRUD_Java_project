package com.mehak.Rest_Api.advice;

import com.mehak.Rest_Api.custom_exception.EmptyInputException;
import com.mehak.Rest_Api.custom_exception.ControllerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<ControllerException> handleEmptyInput(EmptyInputException e) {

        ControllerException ce = new ControllerException(
                e.getErrorCode(),
                e.getErrorMessage()
        );

        return new ResponseEntity<>(ce, HttpStatus.BAD_REQUEST);
    }
}