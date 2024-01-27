package com.edutie.edutiebackend.domain.ruleframework.rules;

import com.edutie.edutiebackend.domain.rule.Error;
import com.edutie.edutiebackend.domain.rule.Rule;
import com.edutie.edutiebackend.domain.ruleframework.mock.Color;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class StrawberryColorRule implements Rule<Color> {


    @Override
    public List<Error> check(Color fruitColor) {
        return fruitColor.equals(Color.RED) ?
                emptyList() :
                singletonList(new Error(this, "Invalid color for this fruit!"));
    }
}
