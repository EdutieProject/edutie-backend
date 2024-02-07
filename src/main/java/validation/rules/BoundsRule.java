package validation.rules;


import validation.Error;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public abstract class BoundsRule<T extends Comparable<T>> implements Rule<T> {
    private final Function<T, Boolean>
            boundsCondition = (object) ->
            (getLowerBound().compareTo(object) > 0 || getLowerBound().compareTo(object) == 0)
                    && getHigherBound().compareTo(object) < 0;

    protected abstract T getLowerBound();

    protected abstract T getHigherBound();

    /**
     * @param object
     * @return
     */
    @Override
    public List<Error> check(T object) {
        return boundsCondition.apply(object) ?
                Collections.emptyList() :
                Collections.singletonList(
                new Error(this.getClass().getSimpleName()+"Violation", "Provided value exceeded expected bounds")
        );
    }
}
