package com.edutie.edutiebackend.domain.ruleframework.rules;

import com.edutie.edutiebackend.domain.rule.Error;
import com.edutie.edutiebackend.domain.rule.Rule;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class FruitTasteBoundsRule implements Rule<Integer> {

    @Override
    public List<Error> check(Integer taste) {
        return taste > 0 && taste <= 10 ?
                emptyList() :
                singletonList(new Error(this, "Bounds exceeded for this type of parameter"));
    }
}
