package com.tinqinacademy.commentsservice.api.services;

import com.tinqinacademy.commentsservice.api.operations.system.deletecommentforroom.DeleteCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.system.deletecommentforroom.DeleteCommentForRoomOutput;

public interface SystemService {
    DeleteCommentForRoomOutput deleteCommentForRoom(DeleteCommentForRoomInput input);
}
