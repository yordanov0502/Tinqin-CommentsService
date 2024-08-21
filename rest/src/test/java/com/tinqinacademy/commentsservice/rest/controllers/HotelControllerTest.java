package com.tinqinacademy.commentsservice.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.commentsservice.api.RestApiRoutes;
import com.tinqinacademy.commentsservice.api.operations.hotel.addcommentforroom.AddCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.hotel.editcommentforroom.UserEditCommentForRoomInput;
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

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY, connection = EmbeddedDatabaseConnection.H2)
class HotelControllerTest {

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
    void getAllCommentsOfRoomOk() throws Exception {
        String roomId = commentRepository.findAll().get(0).getRoomId().toString();

        mvc.perform(get(RestApiRoutes.GET_ALL_ROOM_COMMENTS,roomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllCommentsOfRoomNotFound() throws Exception {
        String roomId = commentRepository.findAll().get(0).getRoomId().toString();

        mvc.perform(get(RestApiRoutes.GET_ALL_ROOM_COMMENTS+"/wrong",roomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound());
    }

    @Test
    void addCommentForRoomCreated() throws Exception {
        String roomId = commentRepository.findAll().get(0).getRoomId().toString();
        String userId = commentRepository.findAll().get(0).getUserId().toString();

        AddCommentForRoomInput input = AddCommentForRoomInput.builder()
                .roomId(roomId)
                .userId(userId)
                .content("The room was cozy and shiny.")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(post(RestApiRoutes.ADD_COMMENT_FOR_ROOM,roomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isCreated());
    }

    @Test
    void addCommentForRoomBadRequest() throws Exception {
        String roomId = "noId";
        String userId = commentRepository.findAll().get(0).getUserId().toString();

        AddCommentForRoomInput input = AddCommentForRoomInput.builder()
                .roomId(roomId)
                .userId(userId)
                .content("The room was cozy and shiny.")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(post(RestApiRoutes.ADD_COMMENT_FOR_ROOM,roomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addCommentForRoomNotFound() throws Exception {
        String roomId = "noId";
        String userId = commentRepository.findAll().get(0).getUserId().toString();

        AddCommentForRoomInput input = AddCommentForRoomInput.builder()
                .roomId(roomId)
                .userId(userId)
                .content("The room was cozy and shiny.")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(post(RestApiRoutes.ADD_COMMENT_FOR_ROOM+"/wrong",roomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound());
    }


    @Test
    void editCommentForRoomOk() throws Exception {
        String commentId = commentRepository.findAll().get(0).getId().toString();
        String userId = commentRepository.findAll().get(0).getUserId().toString();

        UserEditCommentForRoomInput input = UserEditCommentForRoomInput.builder()
                .commentId(commentId)
                .content("The room was cozy, shiny and large.")
                .userId(userId)
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(patch(RestApiRoutes.EDIT_COMMENT_FOR_ROOM,commentId)
                        .contentType("application/json-patch+json")
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(commentId));
    }

    @Test
    void editCommentForRoomBadRequest() throws Exception {
        String commentId = "noId";

        UserEditCommentForRoomInput input = UserEditCommentForRoomInput.builder()
                .commentId(commentId)
                .content("no content")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(patch(RestApiRoutes.EDIT_COMMENT_FOR_ROOM,commentId)
                        .contentType("application/json-patch+json")
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void editCommentForRoomNotFound() throws Exception {
        String commentId = "1234";

        UserEditCommentForRoomInput input = UserEditCommentForRoomInput.builder()
                .commentId(commentId)
                .content("The room was cozy, shiny and large.")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(patch(RestApiRoutes.EDIT_COMMENT_FOR_ROOM+"/wrong",commentId)
                        .contentType("application/json-patch+json")
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound());
    }
}