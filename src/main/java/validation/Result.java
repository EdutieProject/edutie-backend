package validation;

import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

/**
 * Results are object for operation success and failure representation.
 * Result may contain errors that caused the operation to fail. If the result
 * does not contain any errors, it is considered successful;
 */
@Getter
@Value
public class Result {
    List<Error> errors;

    private Result(@NonNull List<Error> errorList) {
        errors = errorList;
    }

    private Result(@NonNull Error error) {
        this.errors = singletonList(error);
    }

    /**
     * Computes the result object based on the errors provided. If the provided
     * error list is empty, return successful result. Failure result is returned
     * otherwise
     *
     * @param errors error list
     * @return Result object
     */
    public static Result fromErrorList(@NonNull List<Error> errors) {
        return new Result(errors);
    }

    /**
     * Creates a blank successful result
     *
     * @return Result object
     */
    public static Result success() {
        return new Result(emptyList());
    }

    /**
     * Creates a failure result with specified error
     *
     * @param error error
     * @return Result object
     */
    public static Result failure(@NonNull Error error) {
        return new Result(singletonList(error));
    }

    /**
     * Indicates whether result is a failure result
     *
     * @return boolean - true/false
     */
    public boolean isFailure() {
        return !isSuccess();
    }

    /**
     * Indicates whether result is successful
     *
     * @return boolean - true/false
     */
    public boolean isSuccess() {
        return errors.isEmpty();
    }
}
