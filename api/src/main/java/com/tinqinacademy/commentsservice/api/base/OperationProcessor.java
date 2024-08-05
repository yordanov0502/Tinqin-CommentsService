package com.tinqinacademy.commentsservice.api.base;

import com.tinqinacademy.commentsservice.api.exceptions.Errors;
import io.vavr.control.Either;

public interface OperationProcessor<I extends  OperationInput, O extends OperationOutput > {
    Either<Errors,O> process(I input);
}
