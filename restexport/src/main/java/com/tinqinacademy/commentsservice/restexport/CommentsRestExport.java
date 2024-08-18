package com.tinqinacademy.commentsservice.restexport;

import com.tinqinacademy.commentsservice.api.RestApiRoutes;
import com.tinqinacademy.commentsservice.api.operations.hotel.addcommentforroom.AddCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.hotel.addcommentforroom.AddCommentForRoomOutput;
import com.tinqinacademy.commentsservice.api.operations.hotel.getallcommentsofroom.GetAllCommentsOfRoomOutput;
import com.tinqinacademy.commentsservice.api.operations.system.deletecommentforroom.DeleteCommentForRoomOutput;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;

@Headers({"Content-Type: application/json"})
public interface CommentsRestExport {

    @RequestLine("GET "+ RestApiRoutes.GET_ALL_ROOM_COMMENTS)
    GetAllCommentsOfRoomOutput getAllCommentsForRoom(@Param String roomId);

    @RequestLine("POST /api/v1/hotel/{roomId}/comment")
    AddCommentForRoomOutput addCommentForRoom(@Param String roomId, @RequestBody AddCommentForRoomInput input);

    @RequestLine("DELETE "+ RestApiRoutes.ADMIN_DELETE_COMMENT_FOR_ROOM)
    DeleteCommentForRoomOutput deleteCommentForRoom(@Param String commentId);
}
