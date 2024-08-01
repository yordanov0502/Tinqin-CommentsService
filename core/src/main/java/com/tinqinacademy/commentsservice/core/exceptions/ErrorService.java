package com.tinqinacademy.commentsservice.core.exceptions;

import org.springframework.web.bind.MethodArgumentNotValidException;

public interface ErrorService {
    ErrorsWrapper handle(MethodArgumentNotValidException exception);
}
