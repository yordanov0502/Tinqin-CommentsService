package com.tinqinacademy.commentsservice.restexport;

import com.tinqinacademy.commentsservice.api.RestApiRoutes;
import com.tinqinacademy.commentsservice.api.operations.hotel.getallcommentsofroom.GetAllCommentsOfRoomOutput;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers({"Content-Type: application/json"})
public interface CommentsRestExport {

    @RequestLine("GET "+ RestApiRoutes.GET_ALL_ROOM_COMMENTS)
    GetAllCommentsOfRoomOutput getAllCommentsForRoom(@Param String roomId);
}
