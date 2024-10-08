package com.tinqinacademy.commentsservice.rest.controllers;

import com.tinqinacademy.commentsservice.api.exceptions.Errors;
import com.tinqinacademy.commentsservice.api.operations.hotel.addcommentforroom.AddCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.hotel.addcommentforroom.AddCommentForRoomOperation;
import com.tinqinacademy.commentsservice.api.operations.hotel.addcommentforroom.AddCommentForRoomOutput;
import com.tinqinacademy.commentsservice.api.operations.hotel.editcommentforroom.UserEditCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.hotel.editcommentforroom.UserEditCommentForRoomOperation;
import com.tinqinacademy.commentsservice.api.operations.hotel.editcommentforroom.UserEditCommentForRoomOutput;
import com.tinqinacademy.commentsservice.api.operations.hotel.getallcommentsofroom.GetAllCommentsOfRoomInput;
import com.tinqinacademy.commentsservice.api.operations.hotel.getallcommentsofroom.GetAllCommentsOfRoomOperation;
import com.tinqinacademy.commentsservice.api.operations.hotel.getallcommentsofroom.GetAllCommentsOfRoomOutput;
import com.tinqinacademy.commentsservice.api.RestApiRoutes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HotelController extends BaseController{

   private final AddCommentForRoomOperation addCommentForRoomOperation;
   private final GetAllCommentsOfRoomOperation getAllCommentsOfRoomOperation;
   private final UserEditCommentForRoomOperation userEditCommentForRoomOperation;


   @Operation(summary = "Get list of all comments for room.",
           description = "Gets list of all commentInfos left for a certain room.")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Successfully returned all commentInfos for a room."),
           @ApiResponse(responseCode = "400", description = "Bad request."),
           @ApiResponse(responseCode = "404", description = "Not found.")
   })
   @GetMapping(RestApiRoutes.GET_ALL_ROOM_COMMENTS)
   public ResponseEntity<?> getAllCommentsOfRoom(@PathVariable String roomId) {

      GetAllCommentsOfRoomInput input = GetAllCommentsOfRoomInput.builder()
              .roomId(roomId)
              .build();

      Either<Errors,GetAllCommentsOfRoomOutput> either = getAllCommentsOfRoomOperation.process(input);

      return mapToResponseEntity(either,HttpStatus.OK);
   }



   @Operation(summary = "Add a comment for room.",
           description = "Leaves a comment regarding a certain room.")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Successfully added comment for a room."),
           @ApiResponse(responseCode = "400", description = "Bad request."),
           @ApiResponse(responseCode = "404", description = "Not found.")
   })
   @PostMapping(RestApiRoutes.ADD_COMMENT_FOR_ROOM)
   public ResponseEntity<?> addCommentForRoom(@PathVariable String roomId,@RequestBody AddCommentForRoomInput inputArg) {

      AddCommentForRoomInput input = inputArg.toBuilder()
              .roomId(roomId)
              .build();

      Either<Errors,AddCommentForRoomOutput> either = addCommentForRoomOperation.process(input);

      return mapToResponseEntity(either,HttpStatus.CREATED);
   }



   @Operation(summary = "Edit a comment for room.",
           description = "User can edit own comment left for a certain room. Last edited date is updated. Info regarding user edited is updated.")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Successfully edited comment for a room."),
           @ApiResponse(responseCode = "400", description = "Bad request."),
           @ApiResponse(responseCode = "404", description = "Not found.")
   })
   @PatchMapping(value = RestApiRoutes.EDIT_COMMENT_FOR_ROOM, consumes = "application/json-patch+json")
   public ResponseEntity<?> editCommentForRoom(@PathVariable String commentId,@RequestBody UserEditCommentForRoomInput inputArg) {

      UserEditCommentForRoomInput input = inputArg.toBuilder()
              .commentId(commentId)
              .build();

      Either<Errors, UserEditCommentForRoomOutput> either = userEditCommentForRoomOperation.process(input);

      return mapToResponseEntity(either,HttpStatus.OK);
   }

}
