package com.tinqinacademy.commentsservice.api.operations.hotel.getallcommentsofroom;

import lombok.*;

import java.time.LocalDate;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private String id;
    private String firstName;
    private String lastName;
    private String content;
    private LocalDate publishDate;
    private LocalDate lastEditedDate;
    private String lastEditedBy;
}
