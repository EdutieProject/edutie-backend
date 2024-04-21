package com.edutie.backend.validation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import validation.Result;

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

}
