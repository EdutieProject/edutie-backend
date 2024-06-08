package validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public final ResponseEntity<Error> handleAllExceptions(Exception exception){
//    public final ResponseEntity<Error> handleAllExceptions(Exception exception, WebRequest request){
//        Error error = new Error(exception.getMessage(), request.getDescription(false));
        Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR.toString(), exception.getMessage());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
