package validation.rules;

import validation.Error;

import java.util.List;

public interface CompoundRule<T, U> {
    List<Error> check(T first, U second);
}
