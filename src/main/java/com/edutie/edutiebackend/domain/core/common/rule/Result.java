package com.edutie.edutiebackend.domain.core.common.rule;

import lombok.Getter;

import java.util.List;

import static java.util.Collections.*;

/**
 *
 * @param <T>
 */
@Getter
public class Result<T> {
    List<RuleError> ruleErrors;
    boolean success;
    T value;

    public Result(T value, List<RuleError> ruleErrorList)
    {
        this.value = value;
        ruleErrors = ruleErrorList;
        success = ruleErrorList.isEmpty();
    }

    public Result(T value, boolean success, RuleError ruleError)
    {
        this.value = value;
        this.success = success;
        this.ruleErrors = singletonList(ruleError);
    }

    public Result(T value, boolean success, List<RuleError> ruleErrors)
    {
        this.value = value;
        this.success = success;
        this.ruleErrors = ruleErrors;
    }

    public static <T> Result<T> fromErrors(T value, List<RuleError> ruleErrors)
    {
        return new Result<>(value, ruleErrors);
    }

    public static <T> Result<T> success(T value)
    {
        return new Result<>(value,true, emptyList());
    }

    public static <T> Result<T> failure(RuleError ruleError)
    {
        return new Result<>(null,false, ruleError);
    }

    public static <T> Result<T> failure(List<RuleError> ruleErrors)
    {
        return new Result<>(null,false, ruleErrors);
    }

    public T getValue()
    {
        if (!success)
            throw new RuleViolationException("Objects violating domain rules cannot be retrieved");

        return value;
    }
}
