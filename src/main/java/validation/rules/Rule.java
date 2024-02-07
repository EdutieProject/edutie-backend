package validation.rules;

import validation.Error;

import java.util.List;

public interface Rule<T> {
    List<Error> check(T object);
}
