package com.tinqinacademy.commentsservice.core.converters.comment;

import com.tinqinacademy.commentsservice.api.operations.hotel.addcommentforroom.AddCommentForRoomOutput;
import com.tinqinacademy.commentsservice.core.converters.BaseConverter;
import com.tinqinacademy.commentsservice.persistence.model.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentToAddCommentForRoomOutput extends BaseConverter<Comment, AddCommentForRoomOutput,CommentToAddCommentForRoomOutput> {

    public CommentToAddCommentForRoomOutput() {
        super(CommentToAddCommentForRoomOutput.class);
    }

    @Override
    protected AddCommentForRoomOutput convertObj(Comment comment) {

        AddCommentForRoomOutput output = AddCommentForRoomOutput.builder()
                .id(comment.getId().toString())
                .build();

        return output;
    }
}
