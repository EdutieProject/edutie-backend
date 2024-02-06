package com.edutie.edutiebackend.domain.core.student.errors;

import com.edutie.edutiebackend.domain.rule.Error;

public class StudentErrors {
    public static Error schoolStageAlternationError()
    {
        return new Error(
                "SchoolStageNotSet",
                "Attempt to alter school stage based on current which does not exist");
    }
}
