package com.tinqinacademy.commentsservice.core.operations.hotel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.tinqinacademy.commentsservice.api.exceptions.Errors;
import com.tinqinacademy.commentsservice.api.operations.hotel.editcommentforroom.UserEditCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.hotel.editcommentforroom.UserEditCommentForRoomOperation;
import com.tinqinacademy.commentsservice.api.operations.hotel.editcommentforroom.UserEditCommentForRoomOutput;
import com.tinqinacademy.commentsservice.core.exceptions.ExceptionService;
import com.tinqinacademy.commentsservice.api.exceptions.custom.NotFoundException;
import com.tinqinacademy.commentsservice.core.operations.BaseOperationProcessor;
import com.tinqinacademy.commentsservice.core.utils.LoggingUtils;
import com.tinqinacademy.commentsservice.persistence.model.entity.Comment;
import com.tinqinacademy.commentsservice.persistence.repository.CommentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class UserEditCommentForRoomOperationProcessor extends BaseOperationProcessor implements UserEditCommentForRoomOperation {

    private final CommentRepository commentRepository;
    private final ObjectMapper objectMapper;

    public UserEditCommentForRoomOperationProcessor(ConversionService conversionService, ExceptionService exceptionService, Validator validator, CommentRepository commentRepository, ObjectMapper objectMapper) {
        super(conversionService, exceptionService, validator);
        this.commentRepository = commentRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Either<Errors, UserEditCommentForRoomOutput> process(UserEditCommentForRoomInput input) {

        return Try.of(() ->{
            log.info(String.format("Start %s %s input: %s",this.getClass().getSimpleName(), LoggingUtils.getMethodName(),input));

            validate(input);

            Comment currComment = commentRepository.findById(UUID.fromString(input.getCommentId()))
                    .orElseThrow(() -> new NotFoundException(String.format("Comment with id[%s] doesn't exist.",input.getCommentId())));

            Comment commentUpdate = conversionService.convert(input, Comment.class);
                    System.out.println(commentUpdate.toString());

            JsonNode currCommentNode = objectMapper.valueToTree(currComment);
            JsonNode commentUpdateNode = objectMapper.valueToTree(commentUpdate);


            try {
                JsonMergePatch patch = JsonMergePatch.fromJson(commentUpdateNode);
                commentUpdate = objectMapper.treeToValue(patch.apply(currCommentNode), Comment.class);
            }
            catch (JsonPatchException | JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            Comment updatedComment = commentRepository.save(commentUpdate);

            UserEditCommentForRoomOutput output = UserEditCommentForRoomOutput.builder()
                    .id(updatedComment.getId().toString())
                    .build();

            log.info(String.format("End %s %s output: %s", this.getClass().getSimpleName(),LoggingUtils.getMethodName(),output));
            return output;})
                .toEither()
                .mapLeft(exceptionService::handle);
    }

    private Comment findCommentById(String commentId) {

        log.info(String.format("Start %s %s input: %s", this.getClass().getSimpleName(),LoggingUtils.getMethodName(),commentId));

        Comment comment = commentRepository
                .findById(UUID.fromString(commentId))
                .orElseThrow(() -> new NotFoundException(String.format("Comment with id[%s] doesn't exist.", commentId)));

        log.info(String.format("End %s %s output: %s", this.getClass().getSimpleName(),LoggingUtils.getMethodName(),comment));

        return comment;
    }
}