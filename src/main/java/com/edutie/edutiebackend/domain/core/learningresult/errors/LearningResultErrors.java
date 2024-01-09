package com.edutie.edutiebackend.domain.core.learningresult.errors;

import com.edutie.edutiebackend.domain.rule.RuleError;

public class LearningResultErrors {
    public static RuleError noAssessmentFound() {
        return new RuleError("NoAssessmentFound", "No assessment has been found of given id");
    }
}
