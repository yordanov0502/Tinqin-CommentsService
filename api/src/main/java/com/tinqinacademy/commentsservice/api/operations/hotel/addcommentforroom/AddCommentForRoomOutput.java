package com.tinqinacademy.commentsservice.api.operations.hotel.addcommentforroom;

import com.tinqinacademy.commentsservice.api.base.OperationOutput;
import lombok.*;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentForRoomOutput implements OperationOutput {
    private String id;
}
