package com.edutie.edutiebackend.domain.core.learningresult.errors;

import com.edutie.edutiebackend.domain.rule.Error;

public class LearningResultErrors {
    public static Error noAssessmentFound() {
        return new Error("NoAssessmentFound", "No assessment has been found of given id");
    }
}
