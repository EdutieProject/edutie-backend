package validation;

/**
 * Exception indicating that the result functionalities were misused and
 * could not operate in the proper way. The most common use of this exception
 * is when an operation needs non-null wrapper result value and the programmer
 * uses the api without checking for the nullity of the value first.
 */
public class ResultMisuseException extends RuntimeException{
    public ResultMisuseException(String message) {
        super(message);
    }
}
