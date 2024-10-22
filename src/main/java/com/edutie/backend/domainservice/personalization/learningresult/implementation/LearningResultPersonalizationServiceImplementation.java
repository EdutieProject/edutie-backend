package com.edutie.backend.domainservice.personalization.learningresult.implementation;

import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domainservice.personalization.learningresult.LearningResultPersonalizationService;
import com.edutie.backend.domainservice.personalization.learningresult.schema.AssessmentSchema;
import com.edutie.backend.infrastucture.external.llm.LargeLanguageModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@RequiredArgsConstructor
@Component
public class LearningResultPersonalizationServiceImplementation implements LearningResultPersonalizationService {
    private final LargeLanguageModelService largeLanguageModelService;
    @Override
    public WrapperResult<LearningResult> personalize(SolutionSubmission solutionSubmission, Student student) {
        return largeLanguageModelService.generateLearningResult(AssessmentSchema.create(student, solutionSubmission));
    }
}
