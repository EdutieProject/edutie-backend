package com.edutie.edutiebackend.domain.core.student.errors;

import com.edutie.edutiebackend.domain.rule.RuleError;

public class StudentErrors {
    public static RuleError schoolStageAlternationError()
    {
        return new RuleError(
                "SchoolStageNotSet",
                "Attempt to alter school stage based on current which does not exist");
    }
}
