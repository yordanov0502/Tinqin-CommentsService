package com.tinqinacademy.commentsservice.core.services;

import com.tinqinacademy.commentsservice.api.operations.hotel.editcommentforroom.EditCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.hotel.editcommentforroom.EditCommentForRoomOutput;
import com.tinqinacademy.commentsservice.api.services.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HotelServiceImpl implements HotelService {




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
