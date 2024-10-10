package com.edutie.backend.domain.personalization;

import validation.Error;

public class PersonalizationError {
    public static Error cantCreateDynamicResourceNoLatestResults() {
        return new Error(
                "NO-LATEST-RESULTS-422",
                "Could not generate dynamic learning resource. Did not found latest results to base the learning resource on."
        );
    }
}
