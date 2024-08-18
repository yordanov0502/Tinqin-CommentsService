package com.tinqinacademy.commentsservice.api.operations.hotel.getallcommentsofroom.content;

import lombok.*;

import java.time.LocalDate;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentInfo {
    private String id;
    private String userId;
    private String content;
    private LocalDate publishDate;
    private LocalDate lastEditedDate;
    private String lastEditedBy;
}
