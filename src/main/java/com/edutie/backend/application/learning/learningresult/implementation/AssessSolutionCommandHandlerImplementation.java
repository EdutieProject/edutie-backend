package com.edutie.backend.application.learning.learningresult.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.learning.learningresult.AssessSolutionCommandHandler;
import com.edutie.backend.application.learning.learningresult.commands.AssessSolutionCommand;
import com.edutie.backend.domainservice.personalization.learningresult.LearningResultPersonalizationService;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.solutionsubmission.persistence.SolutionSubmissionPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import validation.WrapperResult;
import org.springframework.stereotype.*;
import lombok.*;
import lombok.extern.slf4j.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class AssessSolutionCommandHandlerImplementation extends HandlerBase implements AssessSolutionCommandHandler {
	private final LearningResourcePersistence learningResourcePersistence;
	private final StudentPersistence studentPersistence;
	private final SolutionSubmissionPersistence solutionSubmissionPersistence;
	private final LearningResultPersistence learningResultPersistence;
	private final LearningResultPersonalizationService learningResultPersonalizationService;

	@Override
	public WrapperResult<LearningResult> handle(AssessSolutionCommand command) {
		log.info("Handling assessment for student of id {} and learning resource of id {}", command.studentUserId(), command.learningResourceId());
		Student student = studentPersistence.getByAuthorizedUserId(command.studentUserId());
		LearningResource learningResource = learningResourcePersistence.getById(command.learningResourceId()).getValue();

		SolutionSubmission solutionSubmission = SolutionSubmission.create(
				student,
				learningResource.getId(),
				learningResource.getDefinitionType(),
				command.solutionSubmissionText(),
				command.hintsRevealedCount()
		);
		solutionSubmissionPersistence.save(solutionSubmission).throwIfFailure();

		LearningResult learningResult = learningResultPersonalizationService.personalize(solutionSubmission, student).getValue();
		learningResultPersistence.save(learningResult).throwIfFailure();
		return WrapperResult.successWrapper(learningResult);
	}
}
