package com.tinqinacademy.commentsservice.core.converters.comment;

import com.tinqinacademy.commentsservice.api.operations.hotel.addcommentforroom.AddCommentForRoomInput;
import com.tinqinacademy.commentsservice.core.converters.BaseConverter;
import com.tinqinacademy.commentsservice.persistence.model.entity.Comment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AddCommentForRoomInputToComment extends BaseConverter<AddCommentForRoomInput, Comment,AddCommentForRoomInputToComment> {

    public AddCommentForRoomInputToComment() {
        super(AddCommentForRoomInputToComment.class);
    }

    @Override
    protected Comment convertObj(AddCommentForRoomInput input) {

        Comment comment = Comment.builder()
                .roomId(UUID.fromString(input.getRoomId()))
                .userId(UUID.fromString(input.getUserId()))
                .content(input.getContent())
                .build();

        return comment;
    }
}
