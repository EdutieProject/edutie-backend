package com.edutie.edutiebackend.domain.ruleframework.rules;

import com.edutie.edutiebackend.domain.rule.Rule;
import com.edutie.edutiebackend.domain.rule.RuleError;
import com.edutie.edutiebackend.domain.ruleframework.mock.Color;

import static java.util.Collections.*;
import java.util.List;

public class OrangeColorRule implements Rule<Color> {


    @Override
    public List<RuleError> check(Color fruitColor) {
        return fruitColor.equals(Color.ORANGE) ?
                emptyList() :
                singletonList(new RuleError(this, "Invalid color for this fruit!"));
    }
}
