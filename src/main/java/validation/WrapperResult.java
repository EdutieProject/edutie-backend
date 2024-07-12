package validation;

import java.util.function.Function;

/**
 * A wrapper type around the result class. Contains the additional field
 * for a value of type T. It can be used similarly to <CODE>Optional<T></CODE> type,
 * but it additionally contains errors as the Result class does.
 *
 * @param <T> type of contained value
 * @see validation.Result Base Result Class
 */
public class WrapperResult<T> extends Result {
    T value;

    /**
     * Converts the wrapper result to the wrapper result of a different.
     *
     * @param mapper function converting
     * @param <U>    type of new Wrapper Result
     * @return new Wrapper Result of a new type.
     */
    public <U> WrapperResult<U> map(Function<T, U> mapper) {
        if (value == null)
            return new WrapperResult<>(null, this.getError());
        return WrapperResult.successWrapper(mapper.apply(value));
    }

    /**
     * Forces wrapper result conversion into the other type, omitting the value. Use this
     * function only with the failure wrapper.
     *
     * @return Wrapper result converted into other type.
     */
    public <U> WrapperResult<U> into(Class<U> ignored) {
        return this.map(o -> null);
    }

    /**
     * Builder-like function returning this result unchanged, but throwing {@code OperationFailureException}
     * when the result is failure.
     *
     * @return this result.
     */
    @Override
    public WrapperResult<T> throwIfFailure() {
        if (isFailure())
            throw new OperationFailureException(this.getError());
        return this;
    }

    /**
     * Flattens the result, creating a Wrapper Result from a Result class, freeing the
     * wrapped value.
     *
     * @return Result object mirroring this Wrapper Result
     */
    public Result flatten() {
        return new Result(this.getError());
    }

    /**
     * Retrieves the value. May throw {@code NoSuchElementException} when the value is null. The value is null
     * only if the wrapper result is failure, so it is necessary to check using {@code isFailure()}
     * or {@code isSuccess()}
     *
     * @return Wrapped Value
     */
    public T getValue() {
        if (value == null)
            throw new OperationFailureException(this.getError());
        return value;
    }

    protected WrapperResult(T obj, Error error) {
        this.error = error;
        this.value = obj;
    }

}
