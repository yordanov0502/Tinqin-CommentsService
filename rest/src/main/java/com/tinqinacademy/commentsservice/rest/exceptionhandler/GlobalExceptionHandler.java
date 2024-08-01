package com.tinqinacademy.commentsservice.rest.exceptionhandler;

import com.tinqinacademy.commentsservice.core.exceptions.ErrorService;
import com.tinqinacademy.commentsservice.core.exceptions.ErrorsWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorService errorService;

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        ErrorsWrapper errorsWrapper = errorService.handle(exception);
        return new ResponseEntity<>(errorsWrapper.getErrorList(),errorsWrapper.getHttpStatus());
    }

}