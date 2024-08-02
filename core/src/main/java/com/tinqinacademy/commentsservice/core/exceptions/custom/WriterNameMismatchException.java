package com.tinqinacademy.commentsservice.core.exceptions.custom;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class WriterNameMismatchException extends CustomException{

    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public WriterNameMismatchException(String message) {super(message);}
}
