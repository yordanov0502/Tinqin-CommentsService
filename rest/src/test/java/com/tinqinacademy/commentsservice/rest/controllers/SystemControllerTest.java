package com.tinqinacademy.commentsservice.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.commentsservice.api.RestApiRoutes;
import com.tinqinacademy.commentsservice.api.operations.system.editcommentforroom.AdminEditCommentForRoomInput;
import com.tinqinacademy.commentsservice.persistence.model.entity.Comment;
import com.tinqinacademy.commentsservice.persistence.repository.CommentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY, connection = EmbeddedDatabaseConnection.H2)
class SystemControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private CommentRepository commentRepository;

    @BeforeEach
    public void setup() {
        Comment comment = Comment.builder()
                .roomId(UUID.randomUUID())
                .userId(UUID.randomUUID())
                .content("Стаята беше чиста и просторна.")
                .build();

        commentRepository.save(comment);
    }

    @AfterEach
    public void afterEach() {
        commentRepository.deleteAll();
    }

    @Test
    void editCommentForRoomOk() throws Exception {
        String commentId = commentRepository.findAll().get(0).getId().toString();
        String roomId = commentRepository.findAll().get(0).getRoomId().toString();
        String userId = commentRepository.findAll().get(0).getUserId().toString();
        String newContent = "The room was cozy, shiny and large.";

        AdminEditCommentForRoomInput input = AdminEditCommentForRoomInput.builder()
                .commentId(commentId)
                .roomId(roomId)
                .userId(userId)
                .content(newContent)
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(put(RestApiRoutes.ADMIN_EDIT_COMMENT_FOR_ROOM,commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(commentId));

        Comment updatedComment = commentRepository.findById(UUID.fromString(commentId)).get();
        assertEquals(newContent,updatedComment.getContent());
    }

    @Test
    void editCommentForRoomBadRequest() throws Exception {
        String commentId = "noId";
        String roomId = commentRepository.findAll().get(0).getRoomId().toString();
        String userId = commentRepository.findAll().get(0).getUserId().toString();

        AdminEditCommentForRoomInput input = AdminEditCommentForRoomInput.builder()
                .commentId(commentId)
                .roomId(roomId)
                .userId(userId)
                .content("The room was cozy, shiny and large.")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(put(RestApiRoutes.ADMIN_EDIT_COMMENT_FOR_ROOM,commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void editCommentForRoomNotFound() throws Exception {
        String commentId = "noId";
        String roomId = commentRepository.findAll().get(0).getRoomId().toString();
        String userId = commentRepository.findAll().get(0).getUserId().toString();

        AdminEditCommentForRoomInput input = AdminEditCommentForRoomInput.builder()
                .commentId(commentId)
                .roomId(roomId)
                .userId(userId)
                .content("The room was cozy, shiny and large.")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(put(RestApiRoutes.ADMIN_EDIT_COMMENT_FOR_ROOM+"/wrong",commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteCommentForRoomOk() throws Exception {
        String commentId = commentRepository.findAll().get(0).getId().toString();

        mvc.perform(delete(RestApiRoutes.ADMIN_DELETE_COMMENT_FOR_ROOM,commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCommentForRoomNotFound() throws Exception {
        String commentId = commentRepository.findAll().get(0).getId().toString();

        mvc.perform(delete(RestApiRoutes.ADMIN_DELETE_COMMENT_FOR_ROOM+"/wrong",commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound());
    }
}