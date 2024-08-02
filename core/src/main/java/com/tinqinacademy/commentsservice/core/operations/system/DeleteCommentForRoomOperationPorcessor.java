package com.tinqinacademy.commentsservice.core.operations.system;

import com.tinqinacademy.commentsservice.api.error.Errors;
import com.tinqinacademy.commentsservice.api.operations.system.deletecommentforroom.DeleteCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.system.deletecommentforroom.DeleteCommentForRoomOperation;
import com.tinqinacademy.commentsservice.api.operations.system.deletecommentforroom.DeleteCommentForRoomOutput;
import com.tinqinacademy.commentsservice.core.exceptions.ExceptionService;
import com.tinqinacademy.commentsservice.core.exceptions.custom.NotFoundException;
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
public class DeleteCommentForRoomOperationPorcessor extends BaseOperationProcessor implements DeleteCommentForRoomOperation {

    private final CommentRepository commentRepository;

    public DeleteCommentForRoomOperationPorcessor(ConversionService conversionService, ExceptionService exceptionService, Validator validator, CommentRepository commentRepository) {
        super(conversionService, exceptionService, validator);
        this.commentRepository = commentRepository;
    }

    @Override
    public Either<Errors, DeleteCommentForRoomOutput> process(DeleteCommentForRoomInput input) {

        return Try.of(() -> {
            log.info(String.format("Start %s %s input: %s", this.getClass().getSimpleName(), LoggingUtils.getMethodName(),input));

            validate(input);

            Comment comment = findCommentById(input.getCommentId());
            commentRepository.delete(comment);
            DeleteCommentForRoomOutput output = DeleteCommentForRoomOutput.builder().build();

            log.info(String.format("End %s %s output: %s", this.getClass().getSimpleName(),LoggingUtils.getMethodName(),output));

            return output;
        })
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
