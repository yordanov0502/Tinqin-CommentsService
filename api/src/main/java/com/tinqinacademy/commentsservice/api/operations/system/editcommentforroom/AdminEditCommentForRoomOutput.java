package com.tinqinacademy.commentsservice.api.operations.system.editcommentforroom;

import com.tinqinacademy.commentsservice.api.base.OperationOutput;
import lombok.*;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AdminEditCommentForRoomOutput implements OperationOutput {
    private String id;
}
