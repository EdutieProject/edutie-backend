package com.edutie.backend.infrastucture.llm.implementation;

import validation.Error;

public class LlmServiceErrors {

    public static Error exceptionOccurred(Exception exception) {
        return new Error(
                "LLM-SERVICE-500",
                "Exception occurred. " + exception.getMessage()
        );
    }
}
