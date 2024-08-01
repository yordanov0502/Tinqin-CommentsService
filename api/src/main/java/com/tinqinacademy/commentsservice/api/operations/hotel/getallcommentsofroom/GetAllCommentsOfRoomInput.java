package com.tinqinacademy.commentsservice.api.operations.hotel.getallcommentsofroom;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCommentsOfRoomInput {
    @NotBlank
    private String roomId;

}
