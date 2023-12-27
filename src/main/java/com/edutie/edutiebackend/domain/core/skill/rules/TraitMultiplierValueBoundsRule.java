package com.edutie.edutiebackend.domain.core.skill.rules;

import com.edutie.edutiebackend.domain.rule.Rule;
import com.edutie.edutiebackend.domain.rule.RuleError;

import java.util.List;
import java.util.function.Function;

import static java.util.Collections.*;

public class TraitMultiplierValueBoundsRule implements Rule<Double> {

    Function<Double, Boolean> condition = (v) -> v > 0.0 && v < 3.0;

    @Override
    public List<RuleError> check(Double value) {
        return condition.apply(value) ?
                emptyList() :
                singletonList(new RuleError(this, "Trait Multiplier value exceeds defined bounds!"));
    }
}
