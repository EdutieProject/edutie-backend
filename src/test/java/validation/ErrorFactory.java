package validation;

import validation.Error;

public class ErrorFactory {
	public static Error sampleError() {
		return new Error("SampleError", "This is an example error message");
	}
}
