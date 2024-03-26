package validation;

import java.util.function.Function;

/**
 * A wrapper type around the result class. Contains the additional field
 * for a value of type T. It can be used similarly to <CODE>Optional<T></CODE> type,
 * but it additionally contains errors as the Result class does.
 * <b>This class is not meant to be used directly</b>, if you want to create a wrapper
 * result consider using static method provided in the base Result class
 *
 * @param <T> type of contained value
 * @see validation.Result Base Result Class
 */
public class WrapperResult<T> extends Result {
    T value;

    private void checkNullValue() {
        if (value == null)
            throw new ResultMisuseException("The value of the result was null! Could not proceed with the operation.");
    }

    /**
     * Converts the wrapper result to the wrapper result of a different.
     *
     * @param mapper function converting
     * @param <U>    type of new Wrapper Result
     * @return new Wrapper Result of a new type.
     */
    public <U> WrapperResult<U> map(Function<T, U> mapper) {
        checkNullValue();
        return WrapperResult.successWrapper(mapper.apply(value));
    }

    /**
     * Flattens the result, creating a Wrapper Result from a Result class, freeing the
     * wrapped value.
     * @return Result object mirroring this Wrapper Result
     */
    public Result flatten() {
        return new Result(this.getError());
    }

    /**
     * Retrieves the value. May throw {@code ResultMisuseException} when the value is null. The value is null
     * only if the wrapper result is failure, so it is necessary to check using {@code isFailure()}
     * or {@code isSuccess()}
     * @return Wrapped Value
     */
    public T getValue() {
        checkNullValue();
        return value;
    }

    protected WrapperResult(T obj, Error error) {
        this.error = error;
        this.value = obj;
    }

}
