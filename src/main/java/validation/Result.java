package validation;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Results are object for operation success or failure representation.
 * Result may contain errors that caused the operation to fail. If the result
 * does not contain any errors, it is considered successful;
 */
@Getter
public class Result {
    List<Error> errors = new ArrayList<>();

    protected Result() {
    }

    private Result(@NonNull List<Error> errorList) {
        errors = errorList;
    }

    private Result(@NonNull Error error) {
        errors.add(error);
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

    /**
     * Computes the result object based on the errors provided. If the provided
     * error list is empty, return successful result. Failure result is returned
     * otherwise
     *
     * @param errors error list
     * @return Result object
     */
    public static Result fromErrors(@NonNull Error... errors) {
        return new Result(Arrays.stream(errors).toList());
    }

    /**
     * Creates a blank successful result
     *
     * @return Result object
     */
    public static Result success() {
        return new Result();
    }

    /**
     * Creates a failure result with specified error
     *
     * @param error error
     * @return Result object
     */
    public static Result failure(@NonNull Error error) {
        return new Result(error);
    }


    public static <T> WrapperResult<T> successWrapper(T value) {
        return new WrapperResult<>(value);
    }

    public static <T> WrapperResult<T> failureWrapper(Error... errors) {
        return new WrapperResult<>(null, errors);
    }
}
