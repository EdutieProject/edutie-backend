package com.edutie.backend.infrastucture.llm.implementation;

import validation.Error;

import java.io.IOException;

public class LlmServiceErrors {

    public static Error exceptionOccurred(Exception exception) {
        return new Error(
                "LLM-SERVICE-500",
                "Exception occurred. " + exception.getMessage()
        );
    }

    public static Error connectionError(IOException exception) {
        return new Error(
                "LLM-SERVICE-CONNECT-503",
                "Service unavailable due to internal connectivity errors. More: " + exception.getMessage()
        );
    }
}
