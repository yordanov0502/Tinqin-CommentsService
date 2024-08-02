package com.tinqinacademy.commentsservice.api.operations.system.deletecommentforroom;

import com.tinqinacademy.commentsservice.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCommentForRoomInput implements OperationInput {
    @NotBlank
    @UUID
    private String commentId;

}
