package com.edutie.backend.application.learning.assessment.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.learning.assessment.AssessSolutionCommandHandler;
import com.edutie.backend.application.learning.assessment.commands.AssessSolutionCommand;
import com.edutie.backend.domain.personalization.assessmentschema.AssessmentSchema;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.solutionsubmission.persistence.SolutionSubmissionPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.infrastucture.llm.LargeLanguageModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class AssessSolutionCommandHandlerImplementation extends HandlerBase implements AssessSolutionCommandHandler {
    private final LearningResourcePersistence learningResourcePersistence;
    private final StudentPersistence studentPersistence;
    private final SolutionSubmissionPersistence solutionSubmissionPersistence;
    private final LargeLanguageModelService largeLanguageModelService;
    private final LearningResultPersistence learningResultPersistence;

    @Override
    public WrapperResult<LearningResult> handle(AssessSolutionCommand command) {
        LOGGER.info("Handling assessment for student of id {} and learning resource of id {}", command.studentUserId(), command.learningResourceId());
        Student student = studentPersistence.getByAuthorizedUserId(command.studentUserId());
        LearningResource learningResource = learningResourcePersistence.getById(command.learningResourceId()).getValue();
        SolutionSubmission solutionSubmission = SolutionSubmission.create(student, learningResource, command.solutionSubmissionText(), command.hintsRevealedCount());
        solutionSubmissionPersistence.save(solutionSubmission).throwIfFailure();
        LearningResult learningResult = largeLanguageModelService.assessStudentsWork(AssessmentSchema.create(student, solutionSubmission, learningResource)).getValue();
        learningResultPersistence.save(learningResult).throwIfFailure();
        return WrapperResult.successWrapper(learningResult);
    }
}
