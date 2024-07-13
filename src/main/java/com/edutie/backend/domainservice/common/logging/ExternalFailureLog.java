package com.edutie.backend.domainservice.common.logging;

import org.slf4j.Logger;
import validation.Result;

public class ExternalFailureLog {
    public static <T extends Result> T persistenceFailure(T result, Logger logger) {
        logger.info("Persistence error occurred. Error: " + result.getError().toString());
        return result;
    }
    public static <T extends Result> T persistenceFailure(T result, Logger logger, String additionalInfo) {
        logger.info("Persistence error occurred. Error: " + result.getError().toString());
        logger.info("Additional Info: " + additionalInfo);
        return result;
    }
}
