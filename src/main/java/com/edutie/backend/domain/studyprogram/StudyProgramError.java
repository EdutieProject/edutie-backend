package com.edutie.backend.domain.studyprogram;

import validation.Error;

public class StudyProgramError {
    public static validation.Error invalidParentEntity() {
        return new Error("TREE-ELEMENT-INVALID-PARENT-409", "Two navigable entities must be in the same parent category to be linked");
    }
}
