package com.tinqinacademy.commentsservice.api.operations.system.editcommentforroom;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class AdminEditCommentForRoomInput implements OperationInput {
    @JsonIgnore
    @UUID
    private String commentId;
    @NotBlank
    private String roomNo;
    @NotBlank
    @UUID
    private String userId;
    @NotBlank
    private String content;
}
