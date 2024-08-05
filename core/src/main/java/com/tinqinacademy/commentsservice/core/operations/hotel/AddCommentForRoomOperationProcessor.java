package com.tinqinacademy.commentsservice.core.operations.hotel;

import com.tinqinacademy.commentsservice.api.exceptions.Errors;
import com.tinqinacademy.commentsservice.api.operations.hotel.addcommentforroom.AddCommentForRoomInput;
import com.tinqinacademy.commentsservice.api.operations.hotel.addcommentforroom.AddCommentForRoomOperation;
import com.tinqinacademy.commentsservice.api.operations.hotel.addcommentforroom.AddCommentForRoomOutput;
import com.tinqinacademy.commentsservice.core.exceptions.ExceptionService;
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

@Slf4j
@Service
public class AddCommentForRoomOperationProcessor extends BaseOperationProcessor implements AddCommentForRoomOperation {

    private final CommentRepository commentRepository;

    public AddCommentForRoomOperationProcessor(ConversionService conversionService, ExceptionService exceptionService, Validator validator, CommentRepository commentRepository) {
        super(conversionService, exceptionService, validator);
        this.commentRepository = commentRepository;
    }

    @Override
    public Either<Errors, AddCommentForRoomOutput> process(AddCommentForRoomInput input) {

        return Try.of(() -> {
            log.info(String.format("Start %s %s input: %s", this.getClass().getSimpleName(), LoggingUtils.getMethodName(),input));

            validate(input);

            Comment newComment = conversionService.convert(input, Comment.class);
            Comment savedComment = commentRepository.save(newComment);
            AddCommentForRoomOutput output = conversionService.convert(savedComment,AddCommentForRoomOutput.class);

            log.info(String.format("End %s %s output: %s", this.getClass().getSimpleName(),LoggingUtils.getMethodName(),output));

            return output;})
                .toEither()
                .mapLeft(exceptionService::handle);
    }


}
