package com.tinqinacademy.commentsservice.core.operations.system;

import com.tinqinacademy.commentsservice.api.exceptions.Errors;
import com.tinqinacademy.commentsservice.api.operations.system.editcommentforroom.AdminEditCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.system.editcommentforroom.AdminEditCommentForRoomOperation;
import com.tinqinacademy.commentsservice.api.operations.system.editcommentforroom.AdminEditCommentForRoomOutput;
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
public class AdminEditCommentForRoomOperationProcessor extends BaseOperationProcessor implements AdminEditCommentForRoomOperation {

    private final CommentRepository commentRepository;

    public AdminEditCommentForRoomOperationProcessor(ConversionService conversionService, ExceptionService exceptionService, Validator validator, CommentRepository commentRepository) {
        super(conversionService, exceptionService, validator);
        this.commentRepository = commentRepository;
    }

    @Override
    public Either<Errors, AdminEditCommentForRoomOutput> process(AdminEditCommentForRoomInput input) {
        return Try.of(() -> {
            log.info(String.format("Start %s %s input: %s",this.getClass().getSimpleName(), LoggingUtils.getMethodName(),input));

            validate(input);

            Comment currentComment = findCommentById(input.getCommentId());
            Comment commentForUpdate = currentComment.toBuilder()
                    .roomId(UUID.fromString(input.getRoomId()))
                    .content(input.getContent())
                    .lastEditedById(UUID.fromString(input.getUserId()))
                    .build();
            commentRepository.save(commentForUpdate);

            AdminEditCommentForRoomOutput output = AdminEditCommentForRoomOutput.builder()
                    .id(input.getCommentId())
                    .build();

            log.info(String.format("End %s %s output: %s",this.getClass().getSimpleName(),LoggingUtils.getMethodName(),output));

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
