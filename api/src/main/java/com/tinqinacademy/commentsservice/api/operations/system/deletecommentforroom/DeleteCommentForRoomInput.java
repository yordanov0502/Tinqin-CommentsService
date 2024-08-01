package com.tinqinacademy.commentsservice.api.operations.system.deletecommentforroom;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCommentForRoomInput {
    @NotBlank
    private String commentId;

}
