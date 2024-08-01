package com.tinqinacademy.commentsservice.api.operations.hotel.getallcommentsofroom;

import lombok.*;

import java.util.List;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCommentsOfRoomOutput {
    List<Comment> comments;
}
