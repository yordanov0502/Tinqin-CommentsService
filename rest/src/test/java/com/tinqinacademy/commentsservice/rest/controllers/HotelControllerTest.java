package com.tinqinacademy.commentsservice.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.commentsservice.api.RestApiRoutes;
import com.tinqinacademy.commentsservice.api.operations.hotel.addcommentforroom.AddCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.hotel.editcommentforroom.EditCommentForRoomInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class HotelControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllCommentsOfRoomOk() throws Exception {
        String roomId = "11A";

        MvcResult getAllRoomComments = mvc.perform(get(RestApiRoutes.GET_ALL_ROOM_COMMENTS,roomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andReturn();

        assertEquals(200,getAllRoomComments.getResponse().getStatus());
    }

    @Test
    void getAllCommentsOfRoomNotFound() throws Exception {
        String roomId = "11A";

        MvcResult getAllRoomComments = mvc.perform(get(RestApiRoutes.GET_ALL_ROOM_COMMENTS+"/wrong",roomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andReturn();

        assertEquals(404,getAllRoomComments.getResponse().getStatus());
    }

    @Test
    void addCommentForRoomCreated() throws Exception {

        String roomId = "11A";

        AddCommentForRoomInput input = AddCommentForRoomInput.builder()
                .roomId(roomId)
                .firstName("Todor")
                .lastName("Yordanov")
                .content("The room was cozy and shiny.")
                .build();

        String serializedInput = objectMapper.writeValueAsString(input);

        MvcResult addCommentForRoom = mvc.perform(post(RestApiRoutes.ADD_COMMENT_FOR_ROOM,roomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andReturn();

        assertEquals(201,addCommentForRoom.getResponse().getStatus());
    }

    @Test
    void addCommentForRoomBadRequest() throws Exception {

        String roomId = "11A";

        AddCommentForRoomInput input = AddCommentForRoomInput.builder()
                .roomId(roomId)
                .firstName("")
                .lastName("Yordanov")
                .content("The room was cozy and shiny.")
                .build();

        String serializedInput = objectMapper.writeValueAsString(input);

        MvcResult addCommentForRoom = mvc.perform(post(RestApiRoutes.ADD_COMMENT_FOR_ROOM,roomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andReturn();

        assertEquals(400,addCommentForRoom.getResponse().getStatus());
    }

    @Test
    void addCommentForRoomNotFound() throws Exception {

        String roomId = "11A";

        AddCommentForRoomInput input = AddCommentForRoomInput.builder()
                .roomId(roomId)
                .firstName("Todor")
                .lastName("Yordanov")
                .content("The room was cozy and shiny.")
                .build();

        String serializedInput = objectMapper.writeValueAsString(input);

        MvcResult addCommentForRoom = mvc.perform(post(RestApiRoutes.ADD_COMMENT_FOR_ROOM+"/wrong",roomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andReturn();

        assertEquals(404,addCommentForRoom.getResponse().getStatus());
    }


    @Test
    void editCommentForRoomOk() throws Exception {
        String commentId = "1234";

        EditCommentForRoomInput input = EditCommentForRoomInput.builder()
                .commentId(commentId)
                .content("The room was cozy, shiny and large.")
                .build();

        String serializedInput = objectMapper.writeValueAsString(input);

        MvcResult editCommentForRoom = mvc.perform(put(RestApiRoutes.EDIT_COMMENT_FOR_ROOM,commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andReturn();

        assertEquals(200,editCommentForRoom.getResponse().getStatus());
    }

    @Test
    void editCommentForRoomBadRequest() throws Exception {
        String commentId = "1234";

        EditCommentForRoomInput input = EditCommentForRoomInput.builder()
                .commentId(commentId)
                .content("")
                .build();

        String serializedInput = objectMapper.writeValueAsString(input);

        MvcResult editCommentForRoom = mvc.perform(put(RestApiRoutes.EDIT_COMMENT_FOR_ROOM,commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andReturn();

        assertEquals(400,editCommentForRoom.getResponse().getStatus());
    }

    @Test
    void editCommentForRoomNotFound() throws Exception {
        String commentId = "1234";

        EditCommentForRoomInput input = EditCommentForRoomInput.builder()
                .commentId(commentId)
                .content("The room was cozy, shiny and large.")
                .build();

        String serializedInput = objectMapper.writeValueAsString(input);

        MvcResult editCommentForRoom = mvc.perform(put(RestApiRoutes.EDIT_COMMENT_FOR_ROOM+"/wrong",commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andReturn();

        assertEquals(404,editCommentForRoom.getResponse().getStatus());
    }
}