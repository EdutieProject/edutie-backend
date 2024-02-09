package com.edutie.backend.domain.learner.student.errors;

import validation.Error;

public class StudentErrors {
    public static Error schoolStageAlternationError()
    {
        return new Error(
                "SchoolStageNotSet",
                "Attempt to alter school stage based on current which does not exist");
    }
}
