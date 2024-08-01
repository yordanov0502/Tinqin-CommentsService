package com.tinqinacademy.commentsservice.api.services;

import com.tinqinacademy.commentsservice.api.operations.system.deletecommentforroom.DeleteCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.system.deletecommentforroom.DeleteCommentForRoomOutput;
import com.tinqinacademy.commentsservice.api.operations.system.editcommentforroom.EditCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.system.editcommentforroom.EditCommentForRoomOutput;

public interface SystemService {
    EditCommentForRoomOutput editCommentForRoom(EditCommentForRoomInput input);
    DeleteCommentForRoomOutput deleteCommentForRoom(DeleteCommentForRoomInput input);
}
