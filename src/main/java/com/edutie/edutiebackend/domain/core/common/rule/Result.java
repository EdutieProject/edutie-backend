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
    List<Error> errors;
    boolean success;
    T value;

    public Result(T value, List<Error> errorList)
    {
        this.value = value;
        errors = errorList;
        success = errorList.isEmpty();
    }

    public Result(T value, boolean success, Error error)
    {
        this.value = value;
        this.success = success;
        this.errors = singletonList(error);
    }

    public Result(T value, boolean success, List<Error> errors)
    {
        this.value = value;
        this.success = success;
        this.errors = errors;
    }

    public static <T> Result<T> fromErrors(T value, List<Error> errors)
    {
        return new Result<>(value, errors);
    }

    public static <T> Result<T> success(T value)
    {
        return new Result<>(value,true, emptyList());
    }

    public static <T> Result<T> failure(Error error)
    {
        return new Result<>(null,false, error);
    }

    public static <T> Result<T> failure(List<Error> errors)
    {
        return new Result<>(null,false, errors);
    }

    public T getValue()
    {
        if (!success)
            throw new RuleViolationException("Objects violating domain rules cannot be retrieved");

        return value;
    }
}
