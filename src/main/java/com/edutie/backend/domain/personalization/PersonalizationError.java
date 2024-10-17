package com.edutie.backend.domain.personalization;

import com.edutie.backend.domain.personalization.learningresult.identities.AssessmentId;
import validation.Error;

public class PersonalizationError {
    public static Error invalidDifficultyCalculation(AssessmentId assessmentId) {
        return new Error(
                "INVALID-DIFFICULTY-CALCULATION-500",
                String.format("Could not calculate difficulty for the assessment of id %s.", assessmentId)
        );
    }
}
