package com.tinqinacademy.commentsservice.core.exceptions;

import com.tinqinacademy.commentsservice.api.exceptions.Errors;
public interface ExceptionService {
    Errors handle(Throwable throwable);
}
