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

    /**
     * Builder-like function returning this result unchanged, but throwing {@code OperationFailureException}
     * when the result is failure.
     *
     * @return this result.
     */
    public Result throwIfFailure() {
        if (isFailure())
            throw new OperationFailureException(this.getError());
        return this;
    }

    @Override
    public String toString() {
        String className = this.getClass().getSimpleName();
        return className + "[" + (this.isSuccess() ? "SUCCESS" : this.getError()) + "]";
    }

    /**
     * Creates new wrapper result out of this result containing the value
     * provided as the supplier function return value.
     *
     * @param mapper function providing value.
     * @param <T>    type of new wrapper result
     * @return Wrapper Result object
     */
    public <T> WrapperResult<T> map(Supplier<T> mapper) {
        return new WrapperResult<>(mapper.get(), error);
    }

    /**
     * Creates new wrapper result with blank null object as value. New wrapper result is of type
     * provided as the class object.
     *
     * @param clazz class object of the desired type
     * @param <U>   type of new wrapper result
     * @return Wrapper Result object
     */
    public <U> WrapperResult<U> into(Class<U> clazz) {
        return this.map(() -> null);
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
