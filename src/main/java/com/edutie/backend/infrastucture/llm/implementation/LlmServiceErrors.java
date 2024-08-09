package com.edutie.backend.infrastucture.llm.implementation;

import validation.Error;

import java.io.IOException;

public class LlmServiceErrors {

    public static Error invalidStatus(int status, String message) {
        return new Error(
                "LLM-SERVICE-INVALID-STATUS-" + status,
                message
        );
    }

    public static Error exceptionEncountered(Exception exception) {
        return new Error(
                "LLM-SERVICE-EXCEPTION-500",
                "Exception occurred in LLM service infrastructure. " + exception.getMessage()
        );
    }

    public static Error connectionError(IOException exception) {
        return new Error(
                "LLM-SERVICE-CONNECT-503",
                "Could not connect to external llm service. More: " + exception.getMessage()
        );
    }
}
