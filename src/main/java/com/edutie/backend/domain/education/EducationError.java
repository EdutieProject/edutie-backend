package com.edutie.backend.domain.education;

import validation.Error;

public class EducationError {
    public static Error educatorMustBeAuthorError(Class<?> entityClass) {
        return new Error(
                "EDUCATOR-403",
                "To change " + entityClass.getSimpleName() + " you must be an author educator.");
    }

    public static Error educatorInsufficientPermissions() {
        return new Error(
                "EDUCATOR-TYPE-403",
                "To perform this operation educator must be of higher rank (type)");
    }

    public static Error subRequirementsInvalidIndex() {
        return new Error(
                "LEARNING-SUB-REQUIREMENT-400",
                "Operation could not be performed because the index of the provided sub requirement is invalid."
        );
    }

    public static Error reportTemplateParagraphInvalidIndex() {
        return new Error(
                "REPORT-TEMPLATE-PARAGRAPH-400",
                "Operation could not be performed because the index of the provided report template paragraph is invalid."
        );
    }
}
