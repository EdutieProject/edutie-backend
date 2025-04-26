package com.edutie.application.learning.learningsubject.view;

import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;

public record LearningSubjectLearningView(
        LearningSubject learningSubject,
        ElementalRequirementId chosenRequirementId,
        int finishedActivitiesNumber
) {
}
