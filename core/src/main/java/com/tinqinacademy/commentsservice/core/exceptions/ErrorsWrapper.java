package com.tinqinacademy.commentsservice.core.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ErrorsWrapper {

    List<ErrorInfo> errorList;
    HttpStatus httpStatus;
}
