package com.tinqinacademy.commentsservice.rest.controllers;

import com.tinqinacademy.commentsservice.api.operations.system.deletecommentforroom.DeleteCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.system.deletecommentforroom.DeleteCommentForRoomOutput;
import com.tinqinacademy.commentsservice.api.operations.system.editcommentforroom.EditCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.system.editcommentforroom.EditCommentForRoomOutput;
import com.tinqinacademy.commentsservice.api.services.SystemService;
import com.tinqinacademy.commentsservice.api.RestApiRoutes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SystemController {

    private final SystemService systemService;

    @Operation(summary = "Edit a comment for room. (admin)",
            description = "Admin can edit any comment left for a certain room.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully edited comment for a room."),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "404", description = "Not found.")
    })
    @PutMapping(RestApiRoutes.ADMIN_EDIT_COMMENT_FOR_ROOM)
    public ResponseEntity<?> editCommentForRoom(@PathVariable String commentId,@RequestBody EditCommentForRoomInput inputArg) {

        EditCommentForRoomInput input = inputArg.toBuilder()
                .commentId(commentId)
                .build();

        EditCommentForRoomOutput output = systemService.editCommentForRoom(input);

        return new ResponseEntity<>(output, HttpStatus.OK);
    }



    @Operation(summary = "Delete a comment for room. (admin)",
            description = "Admin can delete any comment left for a certain room.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted comment for a room."),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "404", description = "Not found.")
    })
    @DeleteMapping(RestApiRoutes.ADMIN_DELETE_COMMENT_FOR_ROOM)
    public ResponseEntity<?> deleteCommentForRoom(@PathVariable String commentId) {

        DeleteCommentForRoomInput input = DeleteCommentForRoomInput.builder()
                .commentId(commentId)
                .build();

        DeleteCommentForRoomOutput output = systemService.deleteCommentForRoom(input);

        return new ResponseEntity<>(output, HttpStatus.OK);
    }

}
