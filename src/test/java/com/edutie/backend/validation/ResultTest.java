package com.edutie.backend.validation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import validation.Result;
import validation.WrapperResult;

@SpringBootTest
public class ResultTest {

    @Test
    public void successResultTest() {
        var operationResult = Result.success();
        assert operationResult.isSuccess();
    }

    @Test
    public void failureResultTest() {
        var operationResult = Result.failure(ErrorFactory.sampleError());
        assert operationResult.isFailure();
    }

    private WrapperResult<Integer> resultIntoIntegerWrapperSample() {
        Result result = Result.failure(ErrorFactory.sampleError());
        return result.into(Integer.class);
    }

    @Test
    public void resultIntoTest() {
        assert resultIntoIntegerWrapperSample().isFailure();
    }

}
