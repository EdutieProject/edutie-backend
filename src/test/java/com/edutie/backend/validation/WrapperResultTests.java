package com.edutie.backend.validation;

import org.junit.jupiter.api.Test;
import validation.Result;
import validation.WrapperResult;
import org.springframework.boot.test.context.*;

@SpringBootTest
public class WrapperResultTests {

	@Test
	public void successTest() {
		var operationResult = WrapperResult.successWrapper(10);
		assert operationResult.isSuccess();
		assert operationResult.getValue().equals(10);
	}

	@Test
	public void failureTest() {
		var operationResult = WrapperResult.failureWrapper(ErrorFactory.sampleError());
		assert operationResult.isFailure();
		assert operationResult.getValue() == null;
	}

	private WrapperResult<Integer> intoIntegerSampleFunction() {
		WrapperResult<String> wrapperResult = WrapperResult.failureWrapper(ErrorFactory.sampleError());
		return wrapperResult.into(Integer.class);
	}

	@Test
	public void wrapperIntoTest() {
		assert intoIntegerSampleFunction().isFailure();
	}

	private Result flattenSample() {
		WrapperResult<Integer> wrapperResult = WrapperResult.successWrapper(1);
		return wrapperResult.flatten();
	}

	@Test
	public void wrapperFlattenTest() {
		assert flattenSample().isSuccess();
	}

}
