package com.edutie.domain.service.personalization.learningresult.implementation;

import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.entities.submission.base.SolutionSubmissionBase;
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
    private final LearningExperiencePersistence learningExperiencePersistence;

    @Override
    public WrapperResult<LearningResult> personalize(SolutionSubmissionBase solutionSubmissionBase, Student student) {
        AssessmentSchema schema = AssessmentSchema.create(
                learningExperiencePersistence.getById(solutionSubmissionBase.getLearningExperienceId()).getValue(),
                solutionSubmissionBase
        );
        return largeLanguageModelService.generateLearningResult(schema);
    }
}
