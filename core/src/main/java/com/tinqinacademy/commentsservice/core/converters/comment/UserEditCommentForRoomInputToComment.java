package com.tinqinacademy.commentsservice.core.converters.comment;

import com.tinqinacademy.commentsservice.api.operations.hotel.editcommentforroom.UserEditCommentForRoomInput;
import com.tinqinacademy.commentsservice.core.converters.BaseConverter;
import com.tinqinacademy.commentsservice.persistence.model.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class UserEditCommentForRoomInputToComment extends BaseConverter<UserEditCommentForRoomInput, Comment, UserEditCommentForRoomInputToComment> {
    public UserEditCommentForRoomInputToComment() {
        super(UserEditCommentForRoomInputToComment.class);
    }

    @Override
    protected Comment convertObj(UserEditCommentForRoomInput input) {
        Comment comment = Comment.builder()
                .content(input.getContent())
                .build();

        return comment;
    }
}
