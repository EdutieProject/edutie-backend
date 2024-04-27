package validation;

import lombok.Getter;
import lombok.NonNull;

import java.util.function.Supplier;

/**
 * Results are object for operation success or failure representation.
 * Result may contain errors that caused the operation to fail. If the result
 * does not contain any errors, it is considered successful;
 */
@Getter
public class Result {
    Error error = null;

    protected Result() {
    }

    protected Result(Error error) {
        this.error = error;
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
        return error == null;
    }

    public <T> WrapperResult<T> map(Supplier<T> mapper) {
        return new WrapperResult<>(mapper.get(), error);
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
        return new WrapperResult<>(value, null);
    }

    public static <T> WrapperResult<T> failureWrapper(Error error) {
        return new WrapperResult<>(null, error);
    }
}
