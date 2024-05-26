package com.edutie.backend.api.common;


import lombok.Getter;
import validation.Error;
import validation.Result;
import validation.WrapperResult;

/**
 * Api Result made for easy serialization of Result objects and Generic Request Handler
 * compatibility.
 *
 * @param <T>
 * @see Result Validation framework result
 */
@Getter
public class ApiResult<T> {
    T data;
    Error error;
    boolean success;

    public static <T> ApiResult<T> fromWrapper(WrapperResult<T> wrapperResult) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.data = wrapperResult.getValue();
        apiResult.error = wrapperResult.getError();
        apiResult.success = wrapperResult.isSuccess();
        return apiResult;
    }

    public static <T> ApiResult<T> fromResult(Result result) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.data = null;
        apiResult.error = result.getError();
        apiResult.success = result.isSuccess();
        return apiResult;
    }
}
