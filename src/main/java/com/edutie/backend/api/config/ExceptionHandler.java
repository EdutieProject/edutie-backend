package com.edutie.backend.api.config;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import validation.Error;
import validation.OperationFailureException;
import validation.Result;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiResult<?>> handleAllExceptions(Exception exception) {
        if (exception instanceof OperationFailureException ex) {
            return new ResponseEntity<>(
                    ApiResult.fromResult(Result.failure(ex.getError())),
                    new MultiValueMapAdapter<>(new HashMap<>()),
                    GenericRequestHandler.inferStatusCode(ex.getError())
            );
        }

        LOGGER.error("EXCEPTION CAUGHT: " + exception.getClass().getSimpleName() + "\nMessage: " + exception.getMessage());
        LOGGER.debug("DISPLAYING STACK TRACE: \n" + Arrays.stream(exception.getStackTrace()).map(o -> o.toString() + "\n").collect(Collectors.joining()));
        Error error = new Error(
                "SERVER-ERROR-500",
                exception.getClass().getSimpleName() + " occurred. Message: " + exception.getMessage()
        );
        return new ResponseEntity<>(ApiResult.fromResult(Result.failure(error)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
