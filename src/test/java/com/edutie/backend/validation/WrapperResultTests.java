package com.edutie.backend.validation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import validation.Result;

@SpringBootTest
public class WrapperResultTests {

    @Test
    public void successTest() {
        var operationResult = Result.successWrapper(10);
        assert operationResult.isSuccess();
        assert operationResult.getValue().equals(10);
    }

    @Test
    public void failureTest() {
        var operationResult = Result.failureWrapper(ErrorFactory.sampleError());
        assert operationResult.isFailure();
        assert operationResult.getValue() == null;
    }
}
