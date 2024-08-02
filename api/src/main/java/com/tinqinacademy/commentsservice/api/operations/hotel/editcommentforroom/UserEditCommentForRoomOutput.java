package com.tinqinacademy.commentsservice.api.operations.hotel.editcommentforroom;

import com.tinqinacademy.commentsservice.api.base.OperationOutput;
import lombok.*;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserEditCommentForRoomOutput implements OperationOutput {
    private String id;
}
