package com.edutie.backend.api.config;

import com.edutie.backend.api.common.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import validation.Error;
import validation.Result;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiResult<Error>> handleAllExceptions(Exception exception){
        LOGGER.error("EXCEPTION CAUGHT: " + exception.getMessage());
        LOGGER.info("DISPLAYING STACK TRACE: \n" + Arrays.stream(exception.getStackTrace()).map(o -> o.toString() + "\n").collect(Collectors.joining()));
        Error error = new Error("SERVER-ERROR-500", exception.getMessage());
        return new ResponseEntity<>(ApiResult.fromResult(Result.failure(error)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
