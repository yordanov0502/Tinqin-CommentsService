package com.tinqinacademy.commentsservice.core.converters.comment;

import com.tinqinacademy.commentsservice.api.operations.hotel.getallcommentsofroom.GetAllCommentsOfRoomOutput;
import com.tinqinacademy.commentsservice.api.operations.hotel.getallcommentsofroom.content.CommentInfo;
import com.tinqinacademy.commentsservice.core.converters.BaseConverter;
import com.tinqinacademy.commentsservice.persistence.model.entity.Comment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentsToGetAllCommentsOfRoomOutput extends BaseConverter<List<Comment>, GetAllCommentsOfRoomOutput,CommentsToGetAllCommentsOfRoomOutput> {
    public CommentsToGetAllCommentsOfRoomOutput() {
        super(CommentsToGetAllCommentsOfRoomOutput.class);
    }

    @Override
    protected GetAllCommentsOfRoomOutput convertObj(List<Comment> comments) {

        List<CommentInfo> commentList = comments
                .stream()
                .map(comment -> CommentInfo.builder()
                        .id(comment.getId().toString())
                        .firstName(comment.getFirstName())
                        .lastName(comment.getLastName())
                        .content(comment.getContent())
                        .publishDate(comment.getCreatedAt().toLocalDate())
                        .lastEditedDate(comment.getLastEditedDate().toLocalDate())
                        .lastEditedBy(comment.getLastEditedById() != null ? comment.getLastEditedById().toString() : null)
                        .build())
                .toList();

        return GetAllCommentsOfRoomOutput.builder()
                .commentInfos(commentList)
                .build();


    }
}
