package com.tinqinacademy.commentsservice.api.services;

import com.tinqinacademy.commentsservice.api.operations.hotel.editcommentforroom.EditCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.hotel.editcommentforroom.EditCommentForRoomOutput;

public interface HotelService {
    EditCommentForRoomOutput editCommentForRoom(EditCommentForRoomInput input);
}
