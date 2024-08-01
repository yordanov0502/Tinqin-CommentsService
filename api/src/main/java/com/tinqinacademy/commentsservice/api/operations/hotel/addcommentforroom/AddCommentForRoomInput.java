package com.tinqinacademy.commentsservice.api.operations.hotel.addcommentforroom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentForRoomInput {
    @JsonIgnore
    private String roomId;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String content;
}
