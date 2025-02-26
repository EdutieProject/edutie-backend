package com.edutie.domain.service.personalization.learningresult.implementation;

import com.edutie.domain.core.learning.learningexperience.persistence.LearningResourcePersistence;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.solutionsubmission.SolutionSubmission;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.service.personalization.learningresult.LearningResultPersonalizationService;
import com.edutie.domain.service.personalization.learningresult.schema.AssessmentSchema;
import com.edutie.infrastructure.external.llm.LargeLanguageModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@RequiredArgsConstructor
@Component
public class LearningResultPersonalizationServiceImplementation implements LearningResultPersonalizationService {
    private final LargeLanguageModelService largeLanguageModelService;
    private final LearningResourcePersistence learningResourcePersistence;

    @Override
    public WrapperResult<LearningResult> personalize(SolutionSubmission solutionSubmission, Student student) {
        AssessmentSchema schema = AssessmentSchema.create(
                learningResourcePersistence.getById(solutionSubmission.getLearningResourceId()).getValue(),
                solutionSubmission
        );
        return largeLanguageModelService.generateLearningResult(schema);
    }
}
