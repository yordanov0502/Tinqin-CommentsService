package com.tinqinacademy.commentsservice.core.operations.hotel;

import com.tinqinacademy.commentsservice.api.error.Errors;
import com.tinqinacademy.commentsservice.api.operations.hotel.getallcommentsofroom.GetAllCommentsOfRoomInput;
import com.tinqinacademy.commentsservice.api.operations.hotel.getallcommentsofroom.GetAllCommentsOfRoomOperation;
import com.tinqinacademy.commentsservice.api.operations.hotel.getallcommentsofroom.GetAllCommentsOfRoomOutput;
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

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class GetAllCommentsOfRoomOperationProcessor extends BaseOperationProcessor implements GetAllCommentsOfRoomOperation {
    private final CommentRepository commentRepository;

    public GetAllCommentsOfRoomOperationProcessor(ConversionService conversionService, ExceptionService exceptionService, Validator validator,
                                                  CommentRepository commentRepository) {
        super(conversionService, exceptionService, validator);
        this.commentRepository = commentRepository;
    }

    @Override
    public Either<Errors, GetAllCommentsOfRoomOutput> process(GetAllCommentsOfRoomInput input) {

        return Try.of(() -> {
            log.info(String.format("Start %s %s input: %s", this.getClass().getSimpleName(), LoggingUtils.getMethodName(),input));

            validate(input);

            List<Comment> commentsOfRoom = commentRepository.findAllByRoomId(UUID.fromString(input.getRoomId()));
            GetAllCommentsOfRoomOutput output = conversionService.convert(commentsOfRoom,GetAllCommentsOfRoomOutput.class);

            log.info(String.format("End %s %s output: %s", this.getClass().getSimpleName(),LoggingUtils.getMethodName(),output));

            return output;})
                .toEither()
                .mapLeft(exceptionService::handle);
    }

}