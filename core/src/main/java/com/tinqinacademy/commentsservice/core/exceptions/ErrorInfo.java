package com.tinqinacademy.commentsservice.core.exceptions;

import lombok.*;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ErrorInfo {
    private String field;
    private String exceptionMessage;
}
