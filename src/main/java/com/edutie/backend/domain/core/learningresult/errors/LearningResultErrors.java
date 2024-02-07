package com.edutie.backend.domain.core.learningresult.errors;

import validation.Error;

public class LearningResultErrors {
    public static Error noAssessmentFound() {
        return new Error("NoAssessmentFound", "No assessment has been found of given id");
    }
}
