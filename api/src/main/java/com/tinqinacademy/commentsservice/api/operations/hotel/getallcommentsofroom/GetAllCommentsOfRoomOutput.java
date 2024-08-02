package com.tinqinacademy.commentsservice.api.operations.hotel.getallcommentsofroom;

import com.tinqinacademy.commentsservice.api.base.OperationOutput;
import com.tinqinacademy.commentsservice.api.operations.hotel.getallcommentsofroom.content.CommentInfo;
import lombok.*;

import java.util.List;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCommentsOfRoomOutput implements OperationOutput {
    List<CommentInfo> commentInfos;
}
