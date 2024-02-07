package validation;

import lombok.Getter;

import java.util.List;

import static java.util.Collections.*;


@Getter
public class Result {
    List<Error> errors;
    boolean success;

    private Result(List<Error> errorList)
    {
        errors = errorList;
        success = errorList.isEmpty();
    }

    private Result(boolean success, Error error)
    {
        this.success = success;
        this.errors = singletonList(error);
    }

    private Result(boolean success, List<Error> errors)
    {
        this.success = success;
        this.errors = errors;
    }

    public static Result fromErrorList(List<Error> errors)
    {
        return new Result(errors);
    }

    public static  Result success()
    {
        return new Result(true, emptyList());
    }

    public static Result failure(Error error)
    {
        return new Result(false, error);
    }

    public boolean isFailure()
    {
        return !success;
    }
}
