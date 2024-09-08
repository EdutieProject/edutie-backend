package com.edutie.backend.api.config;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import validation.Error;
import validation.*;
import org.springframework.http.*;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.*;
import lombok.extern.slf4j.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public final ResponseEntity<ApiResult<?>> handleAllExceptions(Exception exception) {
		if (exception instanceof OperationFailureException ex) {
			return new ResponseEntity<>(ApiResult.fromResult(Result.failure(ex.getError())), new MultiValueMapAdapter<>(new HashMap<>()), GenericRequestHandler.inferStatusCode(ex.getError()));
		}

		log.error("EXCEPTION CAUGHT: {}\nMessage: {}", exception.getClass().getSimpleName(), exception.getMessage());
		log.debug("DISPLAYING STACK TRACE: \n{}", Arrays.stream(exception.getStackTrace()).map(o -> o.toString() + "\n").collect(Collectors.joining()));
		Error error = new Error("SERVER-ERROR-500", exception.getClass().getSimpleName() + " occurred. Message: " + exception.getMessage());
		return new ResponseEntity<>(ApiResult.fromResult(Result.failure(error)), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public final ResponseEntity<ApiResult<?>> handleConverterExceptions(MethodArgumentTypeMismatchException exception) {
		Error error = new Error("ARGUMENT-TYPE-MISMATCH-400", exception.getMessage());
		return new ResponseEntity<>(ApiResult.fromResult(Result.failure(error)), HttpStatus.BAD_REQUEST);
	}

}
