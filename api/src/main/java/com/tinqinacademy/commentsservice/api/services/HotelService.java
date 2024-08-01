package com.tinqinacademy.commentsservice.api.services;

import com.tinqinacademy.commentsservice.api.operations.hotel.addcommentforroom.AddCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.hotel.addcommentforroom.AddCommentForRoomOutput;
import com.tinqinacademy.commentsservice.api.operations.hotel.editcommentforroom.EditCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.hotel.editcommentforroom.EditCommentForRoomOutput;
import com.tinqinacademy.commentsservice.api.operations.hotel.getallcommentsofroom.GetAllCommentsOfRoomInput;
import com.tinqinacademy.commentsservice.api.operations.hotel.getallcommentsofroom.GetAllCommentsOfRoomOutput;

public interface HotelService {
    GetAllCommentsOfRoomOutput getAllCommentsOfRoom(GetAllCommentsOfRoomInput input);
    AddCommentForRoomOutput addCommentForRoom(AddCommentForRoomInput input);
    EditCommentForRoomOutput editCommentForRoom(EditCommentForRoomInput input);
}
