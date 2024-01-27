package com.edutie.edutiebackend.domain.core.learningresult.rules;

import com.edutie.edutiebackend.domain.rule.Error;
import com.edutie.edutiebackend.domain.rule.Rule;

import java.util.List;
import java.util.function.Function;

import static java.util.Collections.singletonList;
import static java.util.Collections.emptyList;


public class AssessmentPointsBoundsRule implements Rule<Integer> {

    Function<Integer, Boolean> boundsCondition = (value) -> value > 0 && value <=10;

    /**
     * @param value
     * @return
     */
    @Override
    public List<Error> check(Integer value) {
        return boundsCondition.apply(value) ?
                emptyList() :
                singletonList(new Error(this, "Point bounds requirements have not been met."));
    }
}
