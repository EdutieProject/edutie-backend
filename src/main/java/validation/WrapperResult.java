package validation;

import lombok.Getter;

import java.util.Arrays;

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
@Getter
public class WrapperResult<T> extends Result {
    T value;

    protected WrapperResult(T obj, Error... errors) {
        this.errors = Arrays.stream(errors).toList();
        this.value = obj;
    }

}
