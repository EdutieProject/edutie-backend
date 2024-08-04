package com.edutie.backend.infrastucture.knowledgemap.implementation;

import validation.Error;

import java.io.IOException;

public class KnowledgeMapServiceErrors {
    public static Error exceptionEncountered(Exception exception) {
        return new Error(
                "KNOWLEDGE-MAP-EXCEPTION-500",
                "Exception occurred in knowledge map infrastructure"
        );
    }

    public static Error connectionError(IOException exception) {
        return new Error(
                "KNOWLEDGE-MAP-CONNECTION-503",
                "Could not connect to external knowledge map service. More: " + exception.getMessage()
        );
    }

    public static Error invalidStatus(int status, String message) {
        return new Error(
                "KNOWLEDGE-MAP-SERVICE-INVALID-STATUS-" + status,
                message
        );
    }
}
