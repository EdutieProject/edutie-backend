package com.edutie.edutiebackend.domain.rule;

import lombok.Getter;

import java.util.List;

import static java.util.Collections.*;


@Getter
public class Result {
    List<RuleError> ruleErrors;
    boolean success;

    public Result(List<RuleError> ruleErrorList)
    {
        ruleErrors = ruleErrorList;
        success = ruleErrorList.isEmpty();
    }

    public Result(boolean success, RuleError ruleError)
    {
        this.success = success;
        this.ruleErrors = singletonList(ruleError);
    }

    public Result(boolean success, List<RuleError> ruleErrors)
    {
        this.success = success;
        this.ruleErrors = ruleErrors;
    }

    public static Result fromErrorList(List<RuleError> ruleErrors)
    {
        return new Result(ruleErrors);
    }

    public static  Result success()
    {
        return new Result(true, emptyList());
    }

    public static Result failure(RuleError ruleError)
    {
        return new Result(false, ruleError);
    }

    public boolean isFailure()
    {
        return !success;
    }
}
