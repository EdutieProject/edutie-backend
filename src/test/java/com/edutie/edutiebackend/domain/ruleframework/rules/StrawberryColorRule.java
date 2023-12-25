package com.edutie.edutiebackend.domain.ruleframework.rules;

import com.edutie.edutiebackend.domain.rule.Rule;
import com.edutie.edutiebackend.domain.rule.RuleError;
import com.edutie.edutiebackend.domain.ruleframework.mock.Color;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class StrawberryColorRule implements Rule<Color> {


    @Override
    public List<RuleError> check(Color fruitColor) {
        return fruitColor.equals(Color.RED) ?
                emptyList() :
                singletonList(new RuleError(this, "Invalid color for this fruit!"));
    }
}
