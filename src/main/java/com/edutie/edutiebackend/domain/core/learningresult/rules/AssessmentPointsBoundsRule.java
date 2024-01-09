package com.edutie.edutiebackend.domain.core.learningresult.rules;

import com.edutie.edutiebackend.domain.rule.Rule;
import com.edutie.edutiebackend.domain.rule.RuleError;

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
    public List<RuleError> check(Integer value) {
        return boundsCondition.apply(value) ?
                emptyList() :
                singletonList(new RuleError(this, "Point bounds requirements have not been met."));
    }
}
