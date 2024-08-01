package com.tinqinacademy.commentsservice.api.operations.hotel.editcommentforroom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EditCommentForRoomInput {
    @JsonIgnore
    private String commentId;
    @NotBlank
    private String content;
}
