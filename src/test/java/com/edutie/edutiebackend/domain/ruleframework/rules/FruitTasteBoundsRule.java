package com.edutie.edutiebackend.domain.ruleframework.rules;

import com.edutie.edutiebackend.domain.rule.Rule;
import com.edutie.edutiebackend.domain.rule.RuleError;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class FruitTasteBoundsRule implements Rule<Integer> {

    @Override
    public List<RuleError> check(Integer taste) {
        return taste > 0 && taste <= 10 ?
                emptyList() :
                singletonList(new RuleError(this, "Bounds exceeded for this type of parameter"));
    }
}
