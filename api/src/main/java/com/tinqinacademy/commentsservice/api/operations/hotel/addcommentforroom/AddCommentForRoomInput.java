package com.tinqinacademy.commentsservice.api.operations.hotel.addcommentforroom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.commentsservice.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentForRoomInput implements OperationInput {
    @JsonIgnore
    private String roomId;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String content;
}
