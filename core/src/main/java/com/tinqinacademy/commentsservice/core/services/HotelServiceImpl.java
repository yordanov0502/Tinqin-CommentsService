package com.tinqinacademy.commentsservice.core.services;

import com.tinqinacademy.commentsservice.api.operations.hotel.addcommentforroom.AddCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.hotel.addcommentforroom.AddCommentForRoomOutput;
import com.tinqinacademy.commentsservice.api.operations.hotel.editcommentforroom.EditCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.hotel.editcommentforroom.EditCommentForRoomOutput;
import com.tinqinacademy.commentsservice.api.operations.hotel.getallcommentsofroom.Comment;
import com.tinqinacademy.commentsservice.api.operations.hotel.getallcommentsofroom.GetAllCommentsOfRoomInput;
import com.tinqinacademy.commentsservice.api.operations.hotel.getallcommentsofroom.GetAllCommentsOfRoomOutput;
import com.tinqinacademy.commentsservice.api.services.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class HotelServiceImpl implements HotelService {

    @Override
    public GetAllCommentsOfRoomOutput getAllCommentsOfRoom(GetAllCommentsOfRoomInput input) {

        log.info("Start getAllCommentsOfRoom input:{}",input);

        GetAllCommentsOfRoomOutput output = GetAllCommentsOfRoomOutput.builder()
                .comments(List.of(Comment.builder()
                                .id("11A")
                                .firstName("Todor")
                                .lastName("Yordanov")
                                .content("The room was glamorous, clear and cozy.")
                                .publishDate(LocalDate.now())
                                .lastEditedDate(LocalDate.now())
                                .lastEditedBy("Todor Yordanov")
                        .build()))
                .build();

        log.info("End getAllCommentsOfRoom output:{}",output);

        return output;
    }

    @Override
    public AddCommentForRoomOutput addCommentForRoom(AddCommentForRoomInput input) {

        log.info("Start addCommentForRoom input:{}",input);

        AddCommentForRoomOutput output = AddCommentForRoomOutput.builder()
                .id(input.getRoomId())
                .build();

        log.info("End addCommentForRoom output:{}",output);

        return output;
    }

    @Override
    public EditCommentForRoomOutput editCommentForRoom(EditCommentForRoomInput input) {

        log.info("Start editCommentForRoom input:{}",input);

        EditCommentForRoomOutput output = EditCommentForRoomOutput.builder()
                .id(input.getCommentId())
                .build();

        log.info("End editCommentForRoom output:{}",output);

        return output;
    }
}
