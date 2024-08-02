package com.tinqinacademy.commentsservice.core.exceptions;

import com.tinqinacademy.commentsservice.api.error.Errors;
public interface ExceptionService {
    Errors handle(Throwable throwable);
}
