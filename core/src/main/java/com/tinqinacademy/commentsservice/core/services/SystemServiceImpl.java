package com.tinqinacademy.commentsservice.core.services;

import com.tinqinacademy.commentsservice.api.operations.system.deletecommentforroom.DeleteCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.system.deletecommentforroom.DeleteCommentForRoomOutput;
import com.tinqinacademy.commentsservice.api.operations.system.editcommentforroom.EditCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.system.editcommentforroom.EditCommentForRoomOutput;
import com.tinqinacademy.commentsservice.api.services.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SystemServiceImpl implements SystemService {
    @Override
    public EditCommentForRoomOutput editCommentForRoom(EditCommentForRoomInput input) {

        log.info("Start editCommentForRoom input:{}",input);

        EditCommentForRoomOutput output = EditCommentForRoomOutput.builder()
                .id(input.getCommentId())
                .build();

        log.info("End editCommentForRoom output:{}",output);

        return output;
    }

    @Override
    public DeleteCommentForRoomOutput deleteCommentForRoom(DeleteCommentForRoomInput input) {

        log.info("Start deleteCommentForRoom input:{}",input);

        DeleteCommentForRoomOutput output = DeleteCommentForRoomOutput.builder().build();

        log.info("End deleteCommentForRoom output:{}",output);

        return output;
    }
}
