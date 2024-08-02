package com.tinqinacademy.commentsservice.core.services;

import com.tinqinacademy.commentsservice.api.operations.system.deletecommentforroom.DeleteCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.system.deletecommentforroom.DeleteCommentForRoomOutput;
import com.tinqinacademy.commentsservice.api.operations.system.editcommentforroom.AdminEditCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.system.editcommentforroom.AdminEditCommentForRoomOutput;
import com.tinqinacademy.commentsservice.api.services.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SystemServiceImpl implements SystemService {

    @Override
    public DeleteCommentForRoomOutput deleteCommentForRoom(DeleteCommentForRoomInput input) {

        log.info("Start deleteCommentForRoom input:{}",input);

        DeleteCommentForRoomOutput output = DeleteCommentForRoomOutput.builder().build();

        log.info("End deleteCommentForRoom output:{}",output);

        return output;
    }
}
