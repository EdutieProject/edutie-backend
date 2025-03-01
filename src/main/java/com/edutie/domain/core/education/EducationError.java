package com.edutie.domain.core.education;

import validation.Error;

/**
 * Common education subdomain errors.
 */
public class EducationError {
    public static Error learningSubjectEmptyKnowledgeOrigin() {
        return new Error(
                "DOMAIN-LSUB-KNOWLEDGE-ORIGIN-EMPTY-409",
                "The Learning Subject Knowledge Origin is empty thus the operation could not be complete."
        );
    }
}
