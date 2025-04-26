package com.edutie.domain.core.education;

import com.edutie.domain.core.education.educator.Educator;
import validation.Error;

/**
 * Common education subdomain errors.
 */
public class EducationError {
    public static Error unprivilegedEducator(Educator educator) {
        return new Error(
                "DOMAIN-EDUCATOR-UNPRIVILEGED-403",
                String.format("The user of id %s educator profile has no privilege to perform this operation.", educator.getOwnerUserId())
        );
    }

    public static Error learningSubjectEmptyKnowledgeOrigin() {
        return new Error(
                "DOMAIN-LSUB-KNOWLEDGE-ORIGIN-EMPTY-409",
                "The Learning Subject Knowledge Origin is empty thus the operation could not be complete."
        );
    }
}
