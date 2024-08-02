package com.tinqinacademy.commentsservice.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.commentsservice.api.RestApiRoutes;
import com.tinqinacademy.commentsservice.api.operations.system.editcommentforroom.AdminEditCommentForRoomInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SystemControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void editCommentForRoomOk() throws Exception {
        String commentId = "1234";

        AdminEditCommentForRoomInput input = AdminEditCommentForRoomInput.builder()
                .commentId(commentId)
                .roomNo("22B")
                .firstName("Todor")
                .lastName("Yordanov")
                .content("The room was cozy, shiny and large.")
                .build();

        String serializedInput = objectMapper.writeValueAsString(input);

        MvcResult editCommentForRoom = mvc.perform(put(RestApiRoutes.ADMIN_EDIT_COMMENT_FOR_ROOM,commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andReturn();

        assertEquals(200,editCommentForRoom.getResponse().getStatus());
    }

    @Test
    void editCommentForRoomBadRequest() throws Exception {
        String commentId = "1234";

        AdminEditCommentForRoomInput input = AdminEditCommentForRoomInput.builder()
                .commentId(commentId)
                .roomNo("22B")
                .firstName("Todor")
                .lastName("")
                .content("The room was cozy, shiny and large.")
                .build();

        String serializedInput = objectMapper.writeValueAsString(input);

        MvcResult editCommentForRoom = mvc.perform(put(RestApiRoutes.ADMIN_EDIT_COMMENT_FOR_ROOM,commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andReturn();

        assertEquals(400,editCommentForRoom.getResponse().getStatus());
    }

    @Test
    void editCommentForRoomNotFound() throws Exception {
        String commentId = "1234";

        AdminEditCommentForRoomInput input = AdminEditCommentForRoomInput.builder()
                .commentId(commentId)
                .roomNo("22B")
                .firstName("Todor")
                .lastName("Yordanov")
                .content("The room was cozy, shiny and large.")
                .build();

        String serializedInput = objectMapper.writeValueAsString(input);

        MvcResult editCommentForRoom = mvc.perform(put(RestApiRoutes.ADMIN_EDIT_COMMENT_FOR_ROOM+"/wrong",commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andReturn();

        assertEquals(404,editCommentForRoom.getResponse().getStatus());
    }

    @Test
    void deleteCommentForRoomOk() throws Exception {
        String commentId = "1234";

        MvcResult editCommentForRoom = mvc.perform(delete(RestApiRoutes.ADMIN_DELETE_COMMENT_FOR_ROOM,commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andReturn();

        assertEquals(200,editCommentForRoom.getResponse().getStatus());
    }

    @Test
    void deleteCommentForRoomNotFound() throws Exception {
        String commentId = "1234";

        MvcResult editCommentForRoom = mvc.perform(delete(RestApiRoutes.ADMIN_DELETE_COMMENT_FOR_ROOM+"/wrong",commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andReturn();

        assertEquals(404,editCommentForRoom.getResponse().getStatus());
    }
}