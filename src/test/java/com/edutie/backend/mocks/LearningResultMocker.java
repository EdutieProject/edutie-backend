package com.edutie.backend.mocks;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.entities.Assessment;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Set;

public class LearningResultMocker {
    public static LearningResult createLearningResultWithCreatedOnInThePast(
            SolutionSubmission solutionSubmission,
            Feedback feedback,
            Set<Assessment> assessments,
            LocalDateTime createdOnDate
    ) throws Throwable {

        // Create learning result using the recommended constructor method
        LearningResult learningResult = LearningResult.create(solutionSubmission, feedback, assessments);

        // Use reflection to set the createdOn field to a past date
        Field createdOnField = AuditableEntityBase.class.getDeclaredField("createdOn");
        createdOnField.setAccessible(true); // Allow access to private field
        createdOnField.set(learningResult, createdOnDate); // Set the desired past date

        return learningResult;
    }
}
